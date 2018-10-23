package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjalturas.dto.mapper.IInscriptionMapper;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Course;
import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.Learner;
import com.cjalturas.model.dto.GroupDTO;
import com.cjalturas.model.dto.InscriptionDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
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

  
  
  private InputText txtIdLearner;

  private SelectOneMenu cmbLearner;
  
  private Integer idLearnerSel;

  private List<Learner> learners;
  
  
  
  
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

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  @Autowired
  private IInscriptionMapper inscriptionMapper;

  public GroupDetailView() {
    super();
  }

  @PostConstruct
  public void init() {
    loadCoaches();
    loadCourses();
    loadLearners();
    

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

  public String view_detail(ActionEvent evt) {
    selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
    System.out.println("ir ''' " + selectedGroup.getDescription());
    return "learner.xhtml";
  }

//  public String view_detail(String action) {
////    selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
//    System.out.println("ir ''' " + action);
//    return "learner.xhtml";
//  }
//  
  public String view_detail(Group group) {
//  selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
    System.out.println("ir ''' " + group.getDescription());
    return "learner.xhtml";
  }

  public String view_detail() {
    GroupDTO sg = this.getSelectedGroup();
    System.out.println("ir ''' ");
    return "learner.xhtml";
  }

  public String action_inscribe() {
    //Se limpian los campos desde los cuales se realiza la inscripción.
    PageUtils.clearTextBox(txtDescription);
    PageUtils.clearComboBox(cmbCoach);
    selectedGroup = null;
    setShowDialog(true);
    return "";
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
    txtDescription.setValue(selectedGroup.getDescription());
    txtObservations.setValue(selectedGroup.getObservations());

    cmbCoach.setValue(selectedGroup.getIdCoach_Coach());
    cmbCourse.setValue(selectedGroup.getIdCourse_Course());

    calDateStart.setValue(selectedGroup.getDateStart());
    calDateEnd.setValue(selectedGroup.getDateEnd());
    chkStatus.setValue(selectedGroup.getStatus() != null && selectedGroup.getStatus() == 1);

  }

  public String action_save() {
    try {
      if ((selectedGroup == null) && (entity == null)) {
        action_create();
      } else {
        action_modify();
      }
      data = null;
    } catch (Exception e) {
      ZMessManager.addErrorMessage(e.getMessage());
      log.error("Falló la acción de guardado del grupo", e);
    }
    return "";
  }

  public String action_create() {
    try {
      entity = new Group();
      entity.setDescription(FacesUtils.checkString(txtDescription));
      entity.setCoach(getCoach());
      entity.setCourse(getCourse());
      entity.setDateStart(FacesUtils.checkDate(calDateStart));
      entity.setDateEnd(FacesUtils.checkDate(calDateEnd));
      entity.setObservations(FacesUtils.checkString(txtObservations));

      businessDelegatorView.saveGroup(entity);
      ZMessManager.addSaveMessage(ApplicationMessages.getInstance().getMessage("group.save.success"));
      action_clear();
    } catch (Exception e) {
      entity = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de creación del grupo", e);
    }
    return "";
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

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
      Integer idGroup = new Integer(selectedGroup.getIdGroup());
      entity = businessDelegatorView.getGroup(idGroup);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de eliminación del grupo", e);
    }
    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteGroup(entity);
      ZMessManager.addDeleteMessage(ApplicationMessages.getInstance().getMessage("group.delete.success"));
      action_clear();
      data = null;
    } catch (Exception e) {
      log.error("Falló la acción de eliminación del grupo", e);
      throw e;
    }
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
          ;
          ins.setFullNameLearner(inscriptionTmp.getLearner().getPerson().getFullName());

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

  public String action_cert(ActionEvent evt) {
    inscriptionSel = (InscriptionDTO) (evt.getComponent().getAttributes().get("inscriptionSel"));

    System.out.println("certificar a: " + inscriptionSel.getFullNameLearner());

    HashMap<String, String> map = new HashMap<>();
    map.put("#-NAME-#", inscriptionSel.getFullNameLearner());
    map.put("#-DOCUMENT-#", "C.C. 4158585");
    map.put("#-NIVEL-#", "BÁSICO");
    map.put("#-HORAS-#", "8");
    map.put("#-DIAS-#", "15");
    map.put("#-MES-#", "Octubre");
    map.put("#-ANIO-#", "2018");
    map.put("#-INSTRUCTOR1-#", "Uriel Castro");
    map.put("#-INSTRUCTOR1-CHARGE-#", "Entrenador Especializado");
    map.put("#-INSTRUCTOR2-#", "Astrid Elena Jaramillo Torres");
    map.put("#-INSTRUCTOR2-CHARGE-#", "Directora de operaciones");

    new PdfCreator().createPDf2(map);

//    txtDescription.setValue(selectedGroup.getDescription());
//    txtObservations.setValue(selectedGroup.getObservations());
//    
//    cmbCoach.setValue(selectedGroup.getIdCoach_Coach());
//    cmbCourse.setValue(selectedGroup.getIdCourse_Course());
//    
//    calDateStart.setValue(selectedGroup.getDateStart());
//    calDateEnd.setValue(selectedGroup.getDateEnd());
//    chkStatus.setValue(selectedGroup.getStatus()!=null && selectedGroup.getStatus() == 1);
//    
//    PageUtils.enableButton(btnSave);
//    setShowDialog(true);
    return "";
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

  public InputText getTxtIdLearner() {
    return txtIdLearner;
  }

  public void setTxtIdLearner(InputText txtIdLearner) {
    this.txtIdLearner = txtIdLearner;
  }

  public SelectOneMenu getCmbLearner() {
    return cmbLearner;
  }

  public void setCmbLearner(SelectOneMenu cmbLearner) {
    this.cmbLearner = cmbLearner;
  }

  public Integer getIdLearnerSel() {
    return idLearnerSel;
  }

  public void setIdLearnerSel(Integer idLearnerSel) {
    this.idLearnerSel = idLearnerSel;
  }

  public List<Learner> getLearners() {
    return learners;
  }

  public void setLearners(List<Learner> learners) {
    this.learners = learners;
  }
  
}
