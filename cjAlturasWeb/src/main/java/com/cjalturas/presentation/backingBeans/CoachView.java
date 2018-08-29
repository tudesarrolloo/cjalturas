package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.dto.mapper.CoachMapper;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Enterprise;
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
    
    loadCoachInfoFromEntity(selectedCoach);
    
    txtDocument
    cmbTypeId
    txtName
    txtLastname
    txtPhone
    txtEmail
    txtCharge
    txtLicenseSst
    
    txtCharge.setValue(selectedCoach.getCharge());
    txtCharge.setDisabled(false);
    txtLicenseSst.setValue(selectedCoach.getLicenseSst());
    txtLicenseSst.setDisabled(false);
    txtSign.setValue(selectedCoach.getSign());
    txtSign.setDisabled(false);
    txtIdPerson_Person.setValue(selectedCoach.getIdPerson_Person());
    txtIdPerson_Person.setDisabled(false);
    txtIdCoach.setValue(selectedCoach.getIdCoach());
    txtIdCoach.setDisabled(true);
    btnSave.setDisabled(false);
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
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_create() {
    try {
      entity = new Coach();

      Integer idCoach = FacesUtils.checkInteger(txtIdCoach);

      entity.setCharge(FacesUtils.checkString(txtCharge));
      entity.setIdCoach(idCoach);
      entity.setLicenseSst(FacesUtils.checkString(txtLicenseSst));
      entity.setSign(FacesUtils.checkString(txtSign));
      entity.setPerson(
          (FacesUtils.checkInteger(txtIdPerson_Person) != null) ? businessDelegatorView.getPerson(FacesUtils.checkInteger(txtIdPerson_Person)) : null);
      businessDelegatorView.saveCoach(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
      action_clear();
    } catch (Exception e) {
      entity = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_modify() {
    try {
      if (entity == null) {
        Integer idCoach = new Integer(selectedCoach.getIdCoach());
        entity = businessDelegatorView.getCoach(idCoach);
      }

      entity.setCharge(FacesUtils.checkString(txtCharge));
      entity.setLicenseSst(FacesUtils.checkString(txtLicenseSst));
      entity.setSign(FacesUtils.checkString(txtSign));
      entity.setPerson(
          (FacesUtils.checkInteger(txtIdPerson_Person) != null) ? businessDelegatorView.getPerson(FacesUtils.checkInteger(txtIdPerson_Person)) : null);
      businessDelegatorView.updateCoach(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
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
    }

    return "";
  }

  public String action_delete_master() {
    try {
      Integer idCoach = FacesUtils.checkInteger(txtIdCoach);
      entity = businessDelegatorView.getCoach(idCoach);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteCoach(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
      action_clear();
      data = null;
    } catch (Exception e) {
      throw e;
    }
  }

  public String action_closeDialog() {
    setShowDialog(false);
    action_clear();

    return "";
  }

  public String action_modifyWitDTO(String charge, Integer idCoach, String licenseSst, String sign, Integer idPerson_Person) throws Exception {
    try {
      entity.setCharge(FacesUtils.checkString(charge));
      entity.setLicenseSst(FacesUtils.checkString(licenseSst));
      entity.setSign(FacesUtils.checkString(sign));
      businessDelegatorView.updateCoach(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("CoachView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
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

  public InputText getTxtSign() {
    return txtSign;
  }

  public void setTxtSign(InputText txtSign) {
    this.txtSign = txtSign;
  }

  public InputText getTxtIdPerson_Person() {
    return txtIdPerson_Person;
  }

  public void setTxtIdPerson_Person(InputText txtIdPerson_Person) {
    this.txtIdPerson_Person = txtIdPerson_Person;
  }

  public InputText getTxtIdCoach() {
    return txtIdCoach;
  }

  public void setTxtIdCoach(InputText txtIdCoach) {
    this.txtIdCoach = txtIdCoach;
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

  public CoachDTO getSelectedCoach() {
    return selectedCoach;
  }

  public void setSelectedCoach(CoachDTO coach) {
    this.selectedCoach = coach;
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

  public InputNumber getTxtDocument() {
    return txtDocument;
  }

  public void setTxtDocument(InputNumber txtDocument) {
    this.txtDocument = txtDocument;
  }

  public SelectOneMenu getLids() {
    return lids;
  }

  public void setLids(SelectOneMenu lids) {
    this.lids = lids;
  }

  public HashMap<String, String> getTypesId() {
    return typesId;
  }

  public void setTypesId(HashMap<String, String> typesId) {
    this.typesId = typesId;
  }


  
}
