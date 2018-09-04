package com.cjalturas.presentation.backingBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.annotation.SessionScope;

import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Person;
import com.cjalturas.model.TypeId;
import com.cjalturas.model.dto.CoachDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;


/**
 * Bean de la vista de lista y edición de entrenadores.
 * @author Edison
 */
@ManagedBean
@ViewScoped
//@SessionScoped
public class CoachView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(CoachView.class);

  private SelectOneMenu cmbTypeId;

  private InputNumber txtDocument;

  private InputText txtName;

  private InputText txtLastname;

  private InputText txtPhone;

  private InputText txtEmail;

  private InputText txtCharge;

  private InputText txtLicenseSst;

  private UploadedFile file;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<CoachDTO> data;

  private CoachDTO selectedCoach;

  private Coach entity;

  private boolean showDialog;

  private HashMap<String, String> typesId;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public CoachView() {
    super();
  }

  @PostConstruct
  public void init() {
    typesId = TypeId.getTypesId();
  }

  public String action_new() {
    action_clear();
    selectedCoach = null;
    setShowDialog(true);
    return "";
  }

  public String action_clear() {
    entity = null;
    selectedCoach = null;
    PageUtils.clearTextBox(txtDocument);
    PageUtils.clearComboBox(cmbTypeId);
    PageUtils.clearTextBox(txtName);
    PageUtils.clearTextBox(txtLastname);
    PageUtils.clearTextBox(txtPhone);
    PageUtils.clearTextBox(txtEmail);
    PageUtils.clearTextBox(txtCharge);
    PageUtils.clearTextBox(txtLicenseSst);

    PageUtils.enableTextbox(txtDocument);
    PageUtils.disableComboBox(cmbTypeId);
    PageUtils.disableTextbox(txtName);
    PageUtils.disableTextbox(txtLastname);
    PageUtils.disableTextbox(txtPhone);
    PageUtils.disableTextbox(txtEmail);
    PageUtils.disableTextbox(txtCharge);
    PageUtils.disableTextbox(txtLicenseSst);

    PageUtils.disableButton(btnDelete);
    return "";
  }

  public void listener_txtId() {
    try {
      Integer document = FacesUtils.checkInteger(txtDocument);
      entity = findCoachByDocumentPerson(document);
    } catch (Exception e) {
      entity = null;
    }

    PageUtils.enableTextbox(txtDocument);
    PageUtils.enableComboBox(cmbTypeId);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtLastname);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableTextbox(txtEmail);
    PageUtils.enableTextbox(txtCharge);
    PageUtils.enableTextbox(txtLicenseSst);

    if (entity != null) {
      PageUtils.disableTextbox(txtDocument);
      PageUtils.enableButton(btnDelete);

      Person person = entity.getPerson();
      txtDocument.setValue(person.getDocument());
      cmbTypeId.setValue(person.getDocumentType());
      txtName.setValue(person.getName());
      txtLastname.setValue(person.getLastname());
      txtPhone.setValue(person.getPhone());
      txtEmail.setValue(person.getEmail());
      txtCharge.setValue(entity.getCharge());
      txtLicenseSst.setValue(entity.getLicenseSst());
    }
  }

  /**
   * Encuentra un entrenador por el documento de la persona.
   * @param documentPerson documento de la persona con la que se buscará el entrenador.
   * @return instancia del entrenador en caso de que exista.
   * @throws Exception
   */
  private Coach findCoachByDocumentPerson(Integer documentPerson) throws Exception {
    if (documentPerson != null) {
      List<Coach> listCoachs = businessDelegatorView.findCoachByProperty("person.document", String.valueOf(documentPerson));
      if (listCoachs.isEmpty()) {
        return null;
      } else if (listCoachs.size() > 1) {
        log.error("Se encontró más de un instructor con el mismo documento: " + documentPerson);
        throw new RuntimeException("Se encontró más de un instructor con el mismo documento: " + documentPerson);
      }
      return listCoachs.get(0);
    }
    return null;
  }

  public String action_edit(ActionEvent evt) {
    selectedCoach = (CoachDTO) (evt.getComponent().getAttributes().get("selectedCoach"));

    Person person = selectedCoach.getPerson();
    txtDocument.setValue(person.getDocument());
    cmbTypeId.setValue(person.getDocumentType());
    txtName.setValue(person.getName());
    txtLastname.setValue(person.getLastname());
    txtPhone.setValue(person.getPhone());
    txtEmail.setValue(person.getEmail());
    txtCharge.setValue(selectedCoach.getCharge());
    txtLicenseSst.setValue(selectedCoach.getLicenseSst());

    PageUtils.enableTextbox(txtDocument);
    PageUtils.enableComboBox(cmbTypeId);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtLastname);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableTextbox(txtEmail);
    PageUtils.enableTextbox(txtCharge);
    PageUtils.enableTextbox(txtLicenseSst);

    PageUtils.enableButton(btnSave);
    PageUtils.enableButton(btnDelete);
    setShowDialog(true);
    return "";
  }

  public String action_save() {
    try {
      if ((selectedCoach == null) && (entity == null)) {
        action_create();
      } else {
        action_modify();
      }
      data = null;
    } catch (Exception e) {
      ZMessManager.addErrorMessage(e.getMessage());
      log.error("Falló la acción de guardado de la empresa", e);
    }
    return "";
  }

  public String action_create() {
    try {
      Person person = new Person();
      person.setDocument(FacesUtils.checkString(txtDocument));
      person.setDocumentType(FacesUtils.checkString(cmbTypeId));
      person.setName(FacesUtils.checkString(txtName));
      person.setLastname(FacesUtils.checkString(txtLastname));
      person.setPhone(FacesUtils.checkString(txtPhone));
      person.setEmail(FacesUtils.checkString(txtEmail));

      entity = new Coach();
      entity.setCharge(FacesUtils.checkString(txtCharge));
      entity.setLicenseSst(FacesUtils.checkString(txtLicenseSst));
      entity.setPerson(person);
      entity.setSign("PENDING");

      businessDelegatorView.saveCoach(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
      action_clear();
    } catch (Exception e) {
      entity = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de creación del curso", e);
    }
    return "";
  }

  public String action_modify() {
    try {
      if (entity == null) {
        Integer idCoach = new Integer(selectedCoach.getIdCoach());
        entity = businessDelegatorView.getCoach(idCoach);
      }

      Person person = entity.getPerson();
      person.setDocument(FacesUtils.checkString(txtDocument));
      person.setDocumentType(FacesUtils.checkString(cmbTypeId));
      person.setName(FacesUtils.checkString(txtName));
      person.setLastname(FacesUtils.checkString(txtLastname));
      person.setPhone(FacesUtils.checkString(txtPhone));
      person.setEmail(FacesUtils.checkString(txtEmail));

      entity.setCharge(FacesUtils.checkString(txtCharge));
      entity.setLicenseSst(FacesUtils.checkString(txtLicenseSst));
//      entity.setSign(FacesUtils.checkString(txtSign));
      entity.setPerson(person);
      businessDelegatorView.updateCoach(entity);
      ZMessManager.addEditMessage(ApplicationMessages.getInstance().getMessage("enterprise.edit.success"));
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de modificación de la empresa", e);
    }
    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedCoach = (CoachDTO) (evt.getComponent().getAttributes().get("selectedCoach"));
      Integer idCoach = new Integer(selectedCoach.getIdCoach());
      entity = businessDelegatorView.getCoach(idCoach);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de eliminación del curso", e);
    }
    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteCoach(entity);
      ZMessManager.addDeleteMessage(ApplicationMessages.getInstance().getMessage("course.delete.success"));
      action_clear();
      data = null;
    } catch (Exception e) {
      log.error("Falló la acción de eliminación del instructor", e);
      throw e;
    }
  }

  public String action_closeDialog() {
    setShowDialog(false);
    action_clear();
    return "";
  }

  public List<CoachDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataCoach();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<CoachDTO> coachDTO) {
    this.data = coachDTO;
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

  public SelectOneMenu getCmbTypeId() {
    return cmbTypeId;
  }

  public void setCmbTypeId(SelectOneMenu cmbTypeId) {
    this.cmbTypeId = cmbTypeId;
  }

  public InputNumber getTxtDocument() {
    return txtDocument;
  }

  public void setTxtDocument(InputNumber txtDocument) {
    this.txtDocument = txtDocument;
  }

  public InputText getTxtName() {
    return txtName;
  }

  public void setTxtName(InputText txtName) {
    this.txtName = txtName;
  }

  public InputText getTxtLastname() {
    return txtLastname;
  }

  public void setTxtLastname(InputText txtLastname) {
    this.txtLastname = txtLastname;
  }

  public InputText getTxtPhone() {
    return txtPhone;
  }

  public void setTxtPhone(InputText txtPhone) {
    this.txtPhone = txtPhone;
  }

  public InputText getTxtEmail() {
    return txtEmail;
  }

  public void setTxtEmail(InputText txtEmail) {
    this.txtEmail = txtEmail;
  }

  public InputText getTxtCharge() {
    return txtCharge;
  }

  public void setTxtCharge(InputText txtCharge) {
    this.txtCharge = txtCharge;
  }

  public InputText getTxtLicenseSst() {
    return txtLicenseSst;
  }

  public void setTxtLicenseSst(InputText txtLicenseSst) {
    this.txtLicenseSst = txtLicenseSst;
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

  public CoachDTO getSelectedCoach() {
    return selectedCoach;
  }

  public void setSelectedCoach(CoachDTO selectedCoach) {
    this.selectedCoach = selectedCoach;
  }

  public Coach getEntity() {
    return entity;
  }

  public void setEntity(Coach entity) {
    this.entity = entity;
  }

  public HashMap<String, String> getTypesId() {
    return typesId;
  }

  public void setTypesId(HashMap<String, String> typesId) {
    this.typesId = typesId;
  }

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  public void handleFileUpload(FileUploadEvent event) {
    this.setFile(event.getFile());
    FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
    FacesContext.getCurrentInstance().addMessage(null, message);

  }

  public StreamedContent getSayFile() throws IOException {
//    InputStream is;
//    try {
//      if (getFile() != null) {
//        is = getFile().getInputstream();
//        return new DefaultStreamedContent(is, "image/png");
//      }
//    } catch (IOException e) {
//
//    }
//    return new DefaultStreamedContent(null, "image/png");

//    String mimeType = "image/jpg";
//    File sourceimage = new File("E:\\sign1.png"); 
//    InputStream targetStream = new FileInputStream(sourceimage);
//    StreamedContent file = new DefaultStreamedContent(targetStream, mimeType, "firma");
//    return file;

//    String image_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("image_id");
//    System.out.println("image_id: " + image_id);
//
//    if (image_id == null) {
//      File sourceimage = new File("E:\\sign1.png"); 
//    InputStream targetStream = new FileInputStream(sourceimage);
//      
//      DefaultStreamedContent defaultImage = new DefaultStreamedContent(targetStream, "image/png");
//      return defaultImage;
//    }
    if (this.getFile() != null) {
      return new DefaultStreamedContent( this.getFile().getInputstream(), "image/png");
    }else {
      return null;
    }

//    DefaultStreamedContent image = new DefaultStreamedContent(new ByteArrayInputStream(images.get(Integer.valueOf(image_id))), "image/png");
//    File sourceimage = new File("E:\\sign1.png"); 
//    DefaultStreamedContent dbStream = //Get inputstream of a blob eg javax.sql.Blob.getInputStream() API
    
    
    
  }

}
