package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.model.Person;
import com.cjalturas.model.dto.PersonDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class PersonView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(PersonView.class);

  private InputText txtDocument;

  private InputText txtDocumentType;

  private InputText txtEmail;

  private InputText txtLastname;

  private InputText txtName;

  private InputText txtPhone;

  private InputText txtIdPerson;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<PersonDTO> data;

  private PersonDTO selectedPerson;

  private Person entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public PersonView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedPerson = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedPerson = null;

    if (txtDocument != null) {
      txtDocument.setValue(null);
      txtDocument.setDisabled(true);
    }

    if (txtDocumentType != null) {
      txtDocumentType.setValue(null);
      txtDocumentType.setDisabled(true);
    }

    if (txtEmail != null) {
      txtEmail.setValue(null);
      txtEmail.setDisabled(true);
    }

    if (txtLastname != null) {
      txtLastname.setValue(null);
      txtLastname.setDisabled(true);
    }

    if (txtName != null) {
      txtName.setValue(null);
      txtName.setDisabled(true);
    }

    if (txtPhone != null) {
      txtPhone.setValue(null);
      txtPhone.setDisabled(true);
    }

    if (txtIdPerson != null) {
      txtIdPerson.setValue(null);
      txtIdPerson.setDisabled(false);
    }

    if (btnSave != null) {
      btnSave.setDisabled(true);
    }

    if (btnDelete != null) {
      btnDelete.setDisabled(true);
    }

    return "";
  }

  public void listener_txtId() {
    try {
      Integer idPerson = FacesUtils.checkInteger(txtIdPerson);
      entity = (idPerson != null) ? businessDelegatorView.getPerson(idPerson) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtDocument.setDisabled(false);
      txtDocumentType.setDisabled(false);
      txtEmail.setDisabled(false);
      txtLastname.setDisabled(false);
      txtName.setDisabled(false);
      txtPhone.setDisabled(false);
      txtIdPerson.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtDocument.setValue(entity.getDocument());
      txtDocument.setDisabled(false);
      txtDocumentType.setValue(entity.getDocumentType());
      txtDocumentType.setDisabled(false);
      txtEmail.setValue(entity.getEmail());
      txtEmail.setDisabled(false);
      txtLastname.setValue(entity.getLastname());
      txtLastname.setDisabled(false);
      txtName.setValue(entity.getName());
      txtName.setDisabled(false);
      txtPhone.setValue(entity.getPhone());
      txtPhone.setDisabled(false);
      txtIdPerson.setValue(entity.getIdPerson());
      txtIdPerson.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedPerson = (PersonDTO) (evt.getComponent().getAttributes().get("selectedPerson"));
    txtDocument.setValue(selectedPerson.getDocument());
    txtDocument.setDisabled(false);
    txtDocumentType.setValue(selectedPerson.getDocumentType());
    txtDocumentType.setDisabled(false);
    txtEmail.setValue(selectedPerson.getEmail());
    txtEmail.setDisabled(false);
    txtLastname.setValue(selectedPerson.getLastname());
    txtLastname.setDisabled(false);
    txtName.setValue(selectedPerson.getName());
    txtName.setDisabled(false);
    txtPhone.setValue(selectedPerson.getPhone());
    txtPhone.setDisabled(false);
    txtIdPerson.setValue(selectedPerson.getIdPerson());
    txtIdPerson.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedPerson == null) && (entity == null)) {
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
      entity = new Person();

      Integer idPerson = FacesUtils.checkInteger(txtIdPerson);

      entity.setDocument(FacesUtils.checkString(txtDocument));
      entity.setDocumentType(FacesUtils.checkString(txtDocumentType));
      entity.setEmail(FacesUtils.checkString(txtEmail));
      entity.setIdPerson(idPerson);
      entity.setLastname(FacesUtils.checkString(txtLastname));
      entity.setName(FacesUtils.checkString(txtName));
      entity.setPhone(FacesUtils.checkString(txtPhone));
      businessDelegatorView.savePerson(entity);
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
        Integer idPerson = new Integer(selectedPerson.getIdPerson());
        entity = businessDelegatorView.getPerson(idPerson);
      }

      entity.setDocument(FacesUtils.checkString(txtDocument));
      entity.setDocumentType(FacesUtils.checkString(txtDocumentType));
      entity.setEmail(FacesUtils.checkString(txtEmail));
      entity.setLastname(FacesUtils.checkString(txtLastname));
      entity.setName(FacesUtils.checkString(txtName));
      entity.setPhone(FacesUtils.checkString(txtPhone));
      businessDelegatorView.updatePerson(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedPerson = (PersonDTO) (evt.getComponent().getAttributes().get("selectedPerson"));

      Integer idPerson = new Integer(selectedPerson.getIdPerson());
      entity = businessDelegatorView.getPerson(idPerson);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      Integer idPerson = FacesUtils.checkInteger(txtIdPerson);
      entity = businessDelegatorView.getPerson(idPerson);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deletePerson(entity);
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

  public String action_modifyWitDTO(String document, String documentType, String email, Integer idPerson, String lastname, String name, String phone)
      throws Exception {
    try {
      entity.setDocument(FacesUtils.checkString(document));
      entity.setDocumentType(FacesUtils.checkString(documentType));
      entity.setEmail(FacesUtils.checkString(email));
      entity.setLastname(FacesUtils.checkString(lastname));
      entity.setName(FacesUtils.checkString(name));
      entity.setPhone(FacesUtils.checkString(phone));
      businessDelegatorView.updatePerson(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("PersonView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtDocument() {
    return txtDocument;
  }

  public void setTxtDocument(InputText txtDocument) {
    this.txtDocument = txtDocument;
  }

  public InputText getTxtDocumentType() {
    return txtDocumentType;
  }

  public void setTxtDocumentType(InputText txtDocumentType) {
    this.txtDocumentType = txtDocumentType;
  }

  public InputText getTxtEmail() {
    return txtEmail;
  }

  public void setTxtEmail(InputText txtEmail) {
    this.txtEmail = txtEmail;
  }

  public InputText getTxtLastname() {
    return txtLastname;
  }

  public void setTxtLastname(InputText txtLastname) {
    this.txtLastname = txtLastname;
  }

  public InputText getTxtName() {
    return txtName;
  }

  public void setTxtName(InputText txtName) {
    this.txtName = txtName;
  }

  public InputText getTxtPhone() {
    return txtPhone;
  }

  public void setTxtPhone(InputText txtPhone) {
    this.txtPhone = txtPhone;
  }

  public InputText getTxtIdPerson() {
    return txtIdPerson;
  }

  public void setTxtIdPerson(InputText txtIdPerson) {
    this.txtIdPerson = txtIdPerson;
  }

  public List<PersonDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataPerson();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<PersonDTO> personDTO) {
    this.data = personDTO;
  }

  public PersonDTO getSelectedPerson() {
    return selectedPerson;
  }

  public void setSelectedPerson(PersonDTO person) {
    this.selectedPerson = person;
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
}
