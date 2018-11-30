package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjalturas.date.DateProvider;
import com.cjalturas.dto.mapper.IGroupMapper;
import com.cjalturas.dto.mapper.IInscriptionMapper;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Certificate;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Course;
import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.Learner;
import com.cjalturas.model.Person;
import com.cjalturas.model.Status;
import com.cjalturas.model.dto.GroupDTO;
import com.cjalturas.model.dto.InscriptionDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.DelayUtils;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;
import com.cjalturas.utilities.PdfCreator;


/**
 * Bean de la vista de lista y edición de grupos.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class GroupDetailView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(GroupDetailView.class);

//  private static final String GENERATE_CERTIFICATE = "GENERATE_CERTIFICATE";

  private InputText txtDescription;

  private SelectOneMenu cmbCoach;

  private SelectOneMenu cmbCourse;

  private Calendar calDateStart;

  private Calendar calDateEnd;

  private SelectBooleanButton chkStatus;

  private InputTextarea txtObservations;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<GroupDTO> data;

  private GroupDTO selectedGroup;

  private Group entity;

  private boolean showDialog;

  private Integer idCoachSel;

  private Integer idCourseSel;

  private List<Course> courses;

  private List<Coach> coaches;

  private InscriptionDTO inscriptionSel;

  private List<Learner> learners;

  private Learner selectedLearner;

  private CommandButton btnConfirmInscribe;

  private StreamedContent fileCert;

//  private String action;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

//  @Autowired
//  private IInscriptionMapper inscriptionMapper;

  public GroupDetailView() {
    super();
  }

  @PostConstruct
  public void init() {
    loadCoaches();
    loadCourses();
//    loadLearners();

    GroupDTO group = (GroupDTO) this.businessDelegatorView.getParam("group");

    if (group != null) {
      this.setSelectedGroup(group);
//      loadInfoSelectectedGroup();
    }

//    new Timer().schedule(new TimerTask() {
//      @Override
//      public void run() {
//        loadInfoSelectectedGroup();
//      }
//    }, 2000);

  }

  private void loadLearners() {
    try {
      if (learners == null) {
        learners = businessDelegatorView.getLearner();
      }
    } catch (Exception e) {
      log.error("Falló la carga de los aprendices.");
      throw new RuntimeException("Falló la carga de los aprendices.");
    }
  }

  private void loadCourses() {
    try {
      if (courses == null) {
        courses = businessDelegatorView.getCourse();
      }
    } catch (Exception e) {
      log.error("Falló la carga de los cursos.");
      throw new RuntimeException("sFalló la carga de los cursos.");
    }
  }

  private void loadCoaches() {
    try {
      if (coaches == null) {
        coaches = businessDelegatorView.getCoach();
      }
    } catch (Exception e) {
      log.error("Falló la carga de los entrenadores.");
      throw new RuntimeException("Falló la carga de los entrenadores.");
    }
  }

//  public String view_detail(ActionEvent evt) {
//    selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
//    System.out.println("ir ''' " + selectedGroup.getDescription());
//    return "learner.xhtml";
//  }

//  public String view_detail(String action) {
////    selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
//    System.out.println("ir ''' " + action);
//    return "learner.xhtml";
//  }
//  
//  public String view_detail(Group group) {
////  selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
//    System.out.println("ir ''' " + group.getDescription());
//    return "learner.xhtml";
//  }

//  public String view_detail() {
//    GroupDTO sg = this.getSelectedGroup();
//    System.out.println("ir ''' ");
//    return "learner.xhtml";
//  }

  public void action_inscribe() {
    // Se limpian los campos desde los cuales se realiza la inscripción.

    loadLearners();

    PageUtils.clearTextBox(txtDescription);
    PageUtils.clearComboBox(cmbCoach);
//    selectedGroup = null;
    setShowDialog(true);
//    return "";
  }

  public String action_clear() {
    entity = null;
    selectedGroup = null;
    PageUtils.clearTextBox(txtDescription);
    PageUtils.clearComboBox(cmbCoach);
    PageUtils.clearComboBox(cmbCourse);
    PageUtils.clearCalendar(calDateStart);
    PageUtils.clearCalendar(calDateEnd);
    PageUtils.clearBooleanButton(chkStatus, true);
    PageUtils.clearTextArea(txtObservations);

    PageUtils.disableButton(btnDelete);
    return "";
  }

  public String action_edit(ActionEvent evt) {
    selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));

    loadInfoSelectectedGroup();

    PageUtils.enableButton(btnSave);
    setShowDialog(true);
    return "";
  }

  public void loadInfoSelectectedGroup() {
    if (selectedGroup==null) return;
    
    txtDescription.setValue(selectedGroup.getDescription());
    txtObservations.setValue(selectedGroup.getObservations());

    cmbCoach.setValue(selectedGroup.getIdCoach_Coach());
    cmbCourse.setValue(selectedGroup.getIdCourse_Course());

    calDateStart.setValue(selectedGroup.getDateStart());
    calDateEnd.setValue(selectedGroup.getDateEnd());
    chkStatus.setValue(selectedGroup.getStatus() != null && selectedGroup.getStatus() == 1);

  }


  private Coach getCoach() {
    Integer idCoach = getIdCoachSel();
    if (idCoach != null) {
      try {
        return businessDelegatorView.getCoach(idCoach);
      } catch (Exception e) {
        FacesUtils.addErrorMessage(e.getMessage());
        log.error("No se encontró un entrenador con el id: " + idCoach, e);
      }
    }
    return null;
  }

  private Course getCourse() {
    Integer idCourse = getIdCourseSel();
    if (idCourse != null) {
      try {
        return businessDelegatorView.getCourse(idCourse);
      } catch (Exception e) {
        FacesUtils.addErrorMessage(e.getMessage());
        log.error("No se encontró un curso con el id: " + idCourse, e);
      }
    }
    return null;
  }

  public String action_modify() {
    try {
      if (entity == null) {
        Integer idGroup = new Integer(selectedGroup.getIdGroup());
        entity = businessDelegatorView.getGroup(idGroup);
      }

      entity.setDescription(FacesUtils.checkString(txtDescription));
      entity.setCoach(getCoach());
      entity.setCourse(getCourse());
      entity.setDateStart(FacesUtils.checkDate(calDateStart));
      entity.setDateEnd(FacesUtils.checkDate(calDateEnd));
      entity.setObservations(FacesUtils.checkString(txtObservations));

      businessDelegatorView.updateGroup(entity);
      ZMessManager.addEditMessage(ApplicationMessages.getInstance().getMessage("group.edit.success"));
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de modificación del grupo", e);
    }
    return "";
  }

  public String action_closeDialog() {
    setShowDialog(false);
    action_clear();
    return "";
  }

  public List<InscriptionDTO> getData() {
    try {
      Group group = businessDelegatorView.getGroup(getSelectedGroup().getIdGroup());
      Set<Inscription> list = group.getInscriptions();

      try {

        List<InscriptionDTO> inscriptionDTO = new ArrayList<InscriptionDTO>();

        for (Inscription inscriptionTmp : list) {
          InscriptionDTO ins = new InscriptionDTO();
          ins.setIdInscription(inscriptionTmp.getIdInscription());
          ins.setCode_Status(inscriptionTmp.getStatus().getStatus());
          ins.setDateInscription(inscriptionTmp.getDateInscription());
          
          Person learner = inscriptionTmp.getLearner().getPerson();
          ins.setFullNameLearner(learner.getFullName());
          ins.setTypeDocument(learner.getDocumentType());
          ins.setDocument(learner.getDocument());

//            InscriptionDTO inscriptionDTO2 = inscriptionMapper.inscriptionToInscriptionDTO(inscriptionTmp);
          inscriptionDTO.add(ins);
        }

        return inscriptionDTO;
      } catch (Exception e) {
        throw e;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void action_confirm_inscribe() {
    String message;
    ApplicationMessages applicationMessages = ApplicationMessages.getInstance();
    try {
      Learner learnerToInscribe = this.selectedLearner;
      Group group = findGroupById(selectedGroup.getIdGroup());

      Inscription inscription = new Inscription();
      inscription.setDateInscription(DateProvider.getInstance().getCurrentDate());
      inscription.setGroup(group);
      inscription.setLearner(learnerToInscribe);
      inscription.setStatus(Status.ACTIVE);
      businessDelegatorView.saveInscription(inscription);
      message = applicationMessages.getMessage("inscription.save.success");
      ZMessManager.addSaveMessage(message);
    } catch (Exception e) {
      message = e.getMessage();
      ZMessManager.addErrorMessage(message);
      log.error(message, e);
    }
    setShowDialog(false);
  }

  private Group findGroupById(Integer idGroup) {
    try {
      return businessDelegatorView.getGroup(idGroup);
    } catch (Exception e) {
      log.error("No se logró encontrar el grupo con el id: " + getSelectedGroup().getIdGroup(), e);
      throw new RuntimeException("No se logró encontrar el grupo con el id: " + getSelectedGroup().getIdGroup());
    }
  }

  public void action_certify() {
    DelayUtils.delay(DelayUtils.SEC_1);
    Inscription inscriptionToCertify = findSelectedInscription();
    
    
    try {
      Certificate certificate;
      if (inscriptionToCertify.isCertified()) {
        certificate = businessDelegatorView.getCertificate(inscriptionToCertify.getIdInscription());
      }else {
        certificate = businessDelegatorView.certificate(inscriptionToCertify);
      }
      generateCertificate(certificate);
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de certificación del aprendiz", e);
    }
    
    
    
//    inscriptionToCertify.setStatus(Status.CERTIFICATE);
//    this.businessDelegatorView.saveInscription(inscriptionToCertify);
//    
//
//    this.inscriptionSel.setCode_Status(Status.ACTIVE_CODE);
//
//    return findSelectedInscription();
//
//    this.businessDelegatorView.saveInscription(entity);

  }

  private Inscription findSelectedInscription() {
    try {
      return businessDelegatorView.getInscription(this.inscriptionSel.getIdInscription());
    } catch (Exception e) {
      FacesUtils.addErrorMessage("No se puede completar la certificación en este momento.");
      log.error("No se logró encontrar la inscripción que se desea certificar.", e);
    }
    return null;
  }

  public void generateCertificate(Certificate certificate) {
    ApplicationMessages applicationMessages = ApplicationMessages.getInstance();
    HashMap<String, String> map = new HashMap<>();
    map.put("#-CERTIFICATION-#", certificate.getCertification());
    map.put("#-NAME-#", certificate.getLearner());
    map.put("#-DOCUMENT-#", certificate.getLearnerTypeDocument() + " " + certificate.getLearnerDocument());
    map.put("#-LEVEL-#", certificate.getLevel().toUpperCase());
    map.put("#-INTENSITY-#", certificate.getIntensity());
    map.put("#-DAYS-#", certificate.getDays());
    map.put("#-CITY-#", certificate.getCity());
    map.put("#-COACH1-#", certificate.getInstructor1());
    map.put("#-COACH1_CHARGE-#", certificate.getInstructor1Charge());
    map.put("#-COACH1_SIGN-#", certificate.getInstructor1Sign());
    map.put("#-COACH2-#", certificate.getInstructor2());
    map.put("#-COACH2_CHARGE-#", certificate.getInstructor2Charge());
    map.put("#-COACH2_SIGN-#", certificate.getInstructor2Sign());
    map.put("#-DATE-#", certificate.getDate());
    map.put("#-CODE-#", certificate.getCode());
    map.put("#-EMAIL-#", applicationMessages.getMessage("certificate.email"));
    map.put("#-PHONES-#", applicationMessages.getMessage("certificate.phones"));

    DefaultStreamedContent pdfCertificate = new PdfCreator().createPDf2AndDownload(map);

    setFileCert(pdfCertificate);
  }

  /**
   * Encuentra un aprendiz por el documento de la persona.
   * @param documentPerson documento de la persona con la que se buscará el aprendiz.
   * @return instancia del aprendiz en caso de que exista.
   * @throws Exception
   */
  private Learner findLearnerByDocumentPerson(Integer documentPerson) throws Exception {
    if (documentPerson != null) {
      List<Learner> listPersons = businessDelegatorView.findLearnerByProperty("person.document", String.valueOf(documentPerson));
      if (listPersons.isEmpty()) {
        return null;
      } else if (listPersons.size() > 1) {
        log.error("Se encontró más de un aprendiz con el mismo documento: " + documentPerson);
        throw new RuntimeException("Se encontró más de un aprendiz con el mismo documento: " + documentPerson);
      }
      return listPersons.get(0);
    }
    return null;
  }

  public boolean filterByDate(Object value, Object filter, Locale locale) {

    if (filter == null) {
      return true;
    }

    if (value == null) {
      return false;
    }

    return DateUtils.truncatedEquals((Date) filter, (Date) value, java.util.Calendar.DATE);
  }

  public void setData(List<GroupDTO> groupDTO) {
    this.data = groupDTO;
  }

  public GroupDTO getSelectedGroup() {
    return selectedGroup;
  }

  public void setSelectedGroup(GroupDTO group) {
    this.selectedGroup = group;
  }

  public CommandButton getBtnSave() {
    return btnSave;
  }

  public void setBtnSave(CommandButton btnSave) {
    this.btnSave = btnSave;
  }

  public CommandButton getBtnModify() {
    return btnModify;
  }

  public void setBtnModify(CommandButton btnModify) {
    this.btnModify = btnModify;
  }

  public CommandButton getBtnDelete() {
    return btnDelete;
  }

  public void setBtnDelete(CommandButton btnDelete) {
    this.btnDelete = btnDelete;
  }

  public CommandButton getBtnClear() {
    return btnClear;
  }

  public void setBtnClear(CommandButton btnClear) {
    this.btnClear = btnClear;
  }

  public TimeZone getTimeZone() {
    return java.util.TimeZone.getDefault();
  }

  public IBusinessDelegatorView getBusinessDelegatorView() {
    return businessDelegatorView;
  }

  public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
    this.businessDelegatorView = businessDelegatorView;
  }

  public boolean isShowDialog() {
    return showDialog;
  }

  public void setShowDialog(boolean showDialog) {
    this.showDialog = showDialog;
  }

  public InputText getTxtDescription() {
    return txtDescription;
  }

  public void setTxtDescription(InputText txtDescription) {
    this.txtDescription = txtDescription;
  }

  public SelectOneMenu getCmbCoach() {
    return cmbCoach;
  }

  public void setCmbCoach(SelectOneMenu cmbCoach) {
    this.cmbCoach = cmbCoach;
  }

  public SelectOneMenu getCmbCourse() {
    return cmbCourse;
  }

  public void setCmbCourse(SelectOneMenu cmbCourse) {
    this.cmbCourse = cmbCourse;
  }

  public Calendar getCalDateStart() {
    return calDateStart;
  }

  public void setCalDateStart(Calendar calDateStart) {
    this.calDateStart = calDateStart;
  }

  public Calendar getCalDateEnd() {
    return calDateEnd;
  }

  public void setCalDateEnd(Calendar calDateEnd) {
    this.calDateEnd = calDateEnd;
  }

  public SelectBooleanButton getChkStatus() {
    return chkStatus;
  }

  public void setChkStatus(SelectBooleanButton chkStatus) {
    this.chkStatus = chkStatus;
  }

  public Group getEntity() {
    return entity;
  }

  public void setEntity(Group entity) {
    this.entity = entity;
  }

  public Integer getIdCoachSel() {
    return idCoachSel;
  }

  public void setIdCoachSel(Integer idCoachSel) {
    this.idCoachSel = idCoachSel;
  }

  public Integer getIdCourseSel() {
    return idCourseSel;
  }

  public void setIdCourseSel(Integer idCourseSel) {
    this.idCourseSel = idCourseSel;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public List<Coach> getCoaches() {
    return coaches;
  }

  public void setCoaches(List<Coach> coaches) {
    this.coaches = coaches;
  }

  public void setTxtObservations(InputTextarea txtObservations) {
    this.txtObservations = txtObservations;
  }

  public InputTextarea getTxtObservations() {
    return txtObservations;
  }

  public InscriptionDTO getInscriptionSel() {
    return inscriptionSel;
  }

  public void setInscriptionSel(InscriptionDTO inscriptionSel) {
    this.inscriptionSel = inscriptionSel;
  }

  public List<Learner> getLearners() {
    return learners;
  }

  public void setLearners(List<Learner> learners) {
    this.learners = learners;
  }

  public Learner getSelectedLearner() {
    return selectedLearner;
  }

  public void setSelectedLearner(Learner selectedLearner) {
    this.selectedLearner = selectedLearner;
  }

  public CommandButton getBtnConfirmInscribe() {
    return btnConfirmInscribe;
  }

  public void setBtnConfirmInscribe(CommandButton btnConfirmInscribe) {
    this.btnConfirmInscribe = btnConfirmInscribe;
  }

  public void postProcessXLS(Object document) {
    HSSFWorkbook wb = (HSSFWorkbook) document;
    HSSFSheet sheet = wb.getSheetAt(0);
    HSSFRow header = sheet.getRow(0);

//    HSSFRow row = sheet.createRow(0);
//    HSSFCell cellSpecial = row.createCell(0);
//    cellSpecial.setCellValue("increible");

    int rows = sheet.getLastRowNum();

    if (rows < 5) {
      createMinRows(sheet);
    }

    sheet.shiftRows(0, rows, 4);

    sheet.getRow(0).createCell(0).setCellValue("Curso");
    sheet.getRow(0).createCell(1).setCellValue(this.getSelectedGroup().getCourse().getCourse());

    sheet.getRow(1).createCell(0).setCellValue("Grupo");
    sheet.getRow(1).createCell(1).setCellValue(this.getSelectedGroup().getDescription());

    sheet.getRow(2).createCell(0).setCellValue("Entrenador");
    sheet.getRow(2).createCell(1).setCellValue(this.getSelectedGroup().getCoach().getPerson().getFullName());

//    sheet.getRow(0).createCell(1);
    sheet.getRow(0).createCell(2);
    sheet.getRow(0).createCell(3);
    sheet.getRow(0).createCell(4);
    sheet.getRow(1).createCell(2);
    sheet.getRow(1).createCell(3);
    sheet.getRow(1).createCell(4);
    sheet.getRow(2).createCell(2);
    sheet.getRow(2).createCell(3);
    sheet.getRow(2).createCell(4);

    sheet.getRow(3).createCell(0);
    sheet.getRow(3).createCell(1);
    sheet.getRow(3).createCell(2);
    sheet.getRow(3).createCell(3);
    sheet.getRow(3).createCell(4);

    sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));
    sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
    sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 4));

//    CellRangeAddress 
//    sheet.addMergedRegion(0,0,1,4);

    HSSFCellStyle cellWithBorderAndWhite = getCellStyle(wb, HSSFColor.WHITE.index, HSSFCellStyle.BORDER_THIN);

    for (Row row : sheet) {
      for (Cell cell : row) {
//        cell.setCellValue(cell.getStringCellValue().toUpperCase());
        cell.setCellStyle(cellWithBorderAndWhite);

        if (row.getRowNum() == 4 && cell.getColumnIndex() == 3) {
          Cell cellAsistencia = row.createCell(4);
          cellAsistencia.setCellValue("Asistencia");
          cellAsistencia.setCellStyle(cellWithBorderAndWhite);
          sheet.autoSizeColumn(4);
        }
        if (row.getRowNum() > 4 && cell.getColumnIndex() == 3) {
          Cell cellAsistencia = row.createCell(4);
//          cellAsistencia.setCellValue("Asistencia");
          cellAsistencia.setCellStyle(cellWithBorderAndWhite);
        }

      }
    }

    HSSFCellStyle cellNoBorder = getCellStyle(wb, HSSFColor.WHITE.index, HSSFCellStyle.BORDER_NONE);
    HSSFCellStyle cellBold = getCellStyle(wb, HSSFColor.WHITE.index, HSSFCellStyle.BORDER_THIN, HSSFFont.BOLDWEIGHT_BOLD);

    sheet.getRow(0).getCell(0).setCellStyle(cellBold);
    sheet.getRow(1).getCell(0).setCellStyle(cellBold);
    sheet.getRow(2).getCell(0).setCellStyle(cellBold);
    sheet.getRow(4).getCell(0).setCellStyle(cellBold);
    sheet.getRow(4).getCell(1).setCellStyle(cellBold);
    sheet.getRow(4).getCell(2).setCellStyle(cellBold);
    sheet.getRow(4).getCell(3).setCellStyle(cellBold);
    sheet.getRow(4).getCell(4).setCellStyle(cellBold);

    sheet.getRow(3).getCell(0).setCellStyle(cellNoBorder);
    sheet.getRow(3).getCell(1).setCellStyle(cellNoBorder);
    sheet.getRow(3).getCell(2).setCellStyle(cellNoBorder);
    sheet.getRow(3).getCell(3).setCellStyle(cellNoBorder);
    sheet.getRow(3).getCell(4).setCellStyle(cellNoBorder);

//    DataFormatter formatter = new DataFormatter();
//
//    String empno = formatter.formatCellValue(cell0);
//    sheet.getRow(5).getCell(1)
//    

//    sheet.getRow(5).getCell(1).setCellType(HSSFCell.CELL_TYPE_NUMERIC);

//    for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
//        HSSFCell cell = header.getCell(i);
//         
//        cell.setCellStyle(cellStyle);
//    }

//    HSSFWorkbook wb = (HSSFWorkbook) document;
//    HSSFSheet sheet = wb.getSheetAt(0);
//    CellStyle style = wb.createCellStyle();
//    style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
//

  }

  private int createMinRows(HSSFSheet sheet) {
    int noRows = sheet.getLastRowNum();
    if (noRows < 5) {
      sheet.createRow(noRows + 1);
      return createMinRows(sheet);
    }
    return noRows;
  }

  private HSSFCellStyle getCellStyle(HSSFWorkbook wb, short indexColor, short borderStyle, short boldWeight) {
    HSSFCellStyle cellStyle = getCellStyle(wb, indexColor, borderStyle);
    HSSFFont font = wb.createFont();
    font.setBoldweight(boldWeight);
    cellStyle.setFont(font);
    return cellStyle;
  }

  private HSSFCellStyle getCellStyle(HSSFWorkbook wb, short indexColor, short borderStyle) {
    HSSFCellStyle cellStyle = wb.createCellStyle();
//    cellStyle.setFillBackgroundColor(indexColor);
//    cellStyle.setFillForegroundColor(indexColor);
//    cellStyle.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
//    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND); 

    cellStyle.setFillForegroundColor(indexColor);
    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    cellStyle.setBorderLeft(borderStyle);
    cellStyle.setBorderRight(borderStyle);
    cellStyle.setBorderTop(borderStyle);
    cellStyle.setBorderBottom(borderStyle);
    return cellStyle;
  }

  public StreamedContent getFileCert() {

//    inscriptionSel = (InscriptionDTO) (evt.getComponent().getAttributes().get("inscriptionSel"));

//    HashMap<String, String> map = new HashMap<>();
//    map.put("#-NAME-#", "Edison Santiago");
//    map.put("#-DOCUMENT-#", "C.C. 4158585");
//    map.put("#-NIVEL-#", "BÁSICO");
//    map.put("#-HORAS-#", "8");
//    map.put("#-DIAS-#", "15");
//    map.put("#-MES-#", "Octubre");
//    map.put("#-ANIO-#", "2018");
//    map.put("#-INSTRUCTOR1-#", "Uriel Castro");
//    map.put("#-INSTRUCTOR1-CHARGE-#", "Entrenador Especializado");
//    map.put("#-INSTRUCTOR2-#", "Astrid Elena Jaramillo Torres");
//    map.put("#-INSTRUCTOR2-CHARGE-#", "Directora de operaciones");
//
//    DefaultStreamedContent resul = new PdfCreator().createPDf2AndDownload(map);
//
//    
//    
//    
//    return resul;
    return this.fileCert;
  }

  public void setFileCert(StreamedContent fileCert) {
    this.fileCert = fileCert;
  }

//  public String getAction() {
//    return action;
//  }
//
//  public void setAction(String action) {
//    this.action = action;
//  }

}
