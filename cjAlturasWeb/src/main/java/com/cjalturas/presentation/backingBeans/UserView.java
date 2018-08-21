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
import com.cjalturas.model.User;
import com.cjalturas.model.dto.UserDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class UserView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(UserView.class);

  private InputText txtEnabled;

  private InputText txtPassword;

  private InputText txtUsername;

  private InputText txtIdPerson_Person;

  private InputText txtCodeRol_Rol;

  private InputText txtIdUser;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<UserDTO> data;

  private UserDTO selectedUser;

  private User entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public UserView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedUser = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedUser = null;

    if (txtEnabled != null) {
      txtEnabled.setValue(null);
      txtEnabled.setDisabled(true);
    }

    if (txtPassword != null) {
      txtPassword.setValue(null);
      txtPassword.setDisabled(true);
    }

    if (txtUsername != null) {
      txtUsername.setValue(null);
      txtUsername.setDisabled(true);
    }

    if (txtIdPerson_Person != null) {
      txtIdPerson_Person.setValue(null);
      txtIdPerson_Person.setDisabled(true);
    }

    if (txtCodeRol_Rol != null) {
      txtCodeRol_Rol.setValue(null);
      txtCodeRol_Rol.setDisabled(true);
    }

    if (txtIdUser != null) {
      txtIdUser.setValue(null);
      txtIdUser.setDisabled(false);
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
      Integer idUser = FacesUtils.checkInteger(txtIdUser);
      entity = (idUser != null) ? businessDelegatorView.getUser(idUser) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtEnabled.setDisabled(false);
      txtPassword.setDisabled(false);
      txtUsername.setDisabled(false);
      txtIdPerson_Person.setDisabled(false);
      txtCodeRol_Rol.setDisabled(false);
      txtIdUser.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtEnabled.setValue(entity.getEnabled());
      txtEnabled.setDisabled(false);
      txtPassword.setValue(entity.getPassword());
      txtPassword.setDisabled(false);
      txtUsername.setValue(entity.getUsername());
      txtUsername.setDisabled(false);
      txtIdPerson_Person.setValue(entity.getPerson().getIdPerson());
      txtIdPerson_Person.setDisabled(false);
      txtCodeRol_Rol.setValue(entity.getRol().getCodeRol());
      txtCodeRol_Rol.setDisabled(false);
      txtIdUser.setValue(entity.getIdUser());
      txtIdUser.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedUser = (UserDTO) (evt.getComponent().getAttributes().get("selectedUser"));
    txtEnabled.setValue(selectedUser.getEnabled());
    txtEnabled.setDisabled(false);
    txtPassword.setValue(selectedUser.getPassword());
    txtPassword.setDisabled(false);
    txtUsername.setValue(selectedUser.getUsername());
    txtUsername.setDisabled(false);
    txtIdPerson_Person.setValue(selectedUser.getIdPerson_Person());
    txtIdPerson_Person.setDisabled(false);
    txtCodeRol_Rol.setValue(selectedUser.getCodeRol_Rol());
    txtCodeRol_Rol.setDisabled(false);
    txtIdUser.setValue(selectedUser.getIdUser());
    txtIdUser.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedUser == null) && (entity == null)) {
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
      entity = new User();

      Integer idUser = FacesUtils.checkInteger(txtIdUser);

      entity.setEnabled(FacesUtils.checkString(txtEnabled));
      entity.setIdUser(idUser);
      entity.setPassword(FacesUtils.checkString(txtPassword));
      entity.setUsername(FacesUtils.checkString(txtUsername));
      entity.setPerson(
          (FacesUtils.checkInteger(txtIdPerson_Person) != null) ? businessDelegatorView.getPerson(FacesUtils.checkInteger(txtIdPerson_Person)) : null);
      entity.setRol((FacesUtils.checkString(txtCodeRol_Rol) != null) ? businessDelegatorView.getRol(FacesUtils.checkString(txtCodeRol_Rol)) : null);
      businessDelegatorView.saveUser(entity);
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
        Integer idUser = new Integer(selectedUser.getIdUser());
        entity = businessDelegatorView.getUser(idUser);
      }

      entity.setEnabled(FacesUtils.checkString(txtEnabled));
      entity.setPassword(FacesUtils.checkString(txtPassword));
      entity.setUsername(FacesUtils.checkString(txtUsername));
      entity.setPerson(
          (FacesUtils.checkInteger(txtIdPerson_Person) != null) ? businessDelegatorView.getPerson(FacesUtils.checkInteger(txtIdPerson_Person)) : null);
      entity.setRol((FacesUtils.checkString(txtCodeRol_Rol) != null) ? businessDelegatorView.getRol(FacesUtils.checkString(txtCodeRol_Rol)) : null);
      businessDelegatorView.updateUser(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedUser = (UserDTO) (evt.getComponent().getAttributes().get("selectedUser"));

      Integer idUser = new Integer(selectedUser.getIdUser());
      entity = businessDelegatorView.getUser(idUser);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      Integer idUser = FacesUtils.checkInteger(txtIdUser);
      entity = businessDelegatorView.getUser(idUser);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteUser(entity);
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

  public String action_modifyWitDTO(String enabled, Integer idUser, String password, String username, Integer idPerson_Person, String codeRol_Rol)
      throws Exception {
    try {
      entity.setEnabled(FacesUtils.checkString(enabled));
      entity.setPassword(FacesUtils.checkString(password));
      entity.setUsername(FacesUtils.checkString(username));
      businessDelegatorView.updateUser(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("UserView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtEnabled() {
    return txtEnabled;
  }

  public void setTxtEnabled(InputText txtEnabled) {
    this.txtEnabled = txtEnabled;
  }

  public InputText getTxtPassword() {
    return txtPassword;
  }

  public void setTxtPassword(InputText txtPassword) {
    this.txtPassword = txtPassword;
  }

  public InputText getTxtUsername() {
    return txtUsername;
  }

  public void setTxtUsername(InputText txtUsername) {
    this.txtUsername = txtUsername;
  }

  public InputText getTxtIdPerson_Person() {
    return txtIdPerson_Person;
  }

  public void setTxtIdPerson_Person(InputText txtIdPerson_Person) {
    this.txtIdPerson_Person = txtIdPerson_Person;
  }

  public InputText getTxtCodeRol_Rol() {
    return txtCodeRol_Rol;
  }

  public void setTxtCodeRol_Rol(InputText txtCodeRol_Rol) {
    this.txtCodeRol_Rol = txtCodeRol_Rol;
  }

  public InputText getTxtIdUser() {
    return txtIdUser;
  }

  public void setTxtIdUser(InputText txtIdUser) {
    this.txtIdUser = txtIdUser;
  }

  public List<UserDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataUser();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<UserDTO> userDTO) {
    this.data = userDTO;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO user) {
    this.selectedUser = user;
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
