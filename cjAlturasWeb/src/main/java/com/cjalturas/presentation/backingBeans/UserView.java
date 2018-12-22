package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.dto.mapper.MapperUtil;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Person;
import com.cjalturas.model.Rol;
import com.cjalturas.model.TypeId;
import com.cjalturas.model.User;
import com.cjalturas.model.dto.UserDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;


/**
 * Bean de la vista de lista y edición de usuarios.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class UserView implements Serializable {

  private static final long serialVersionUID = -3753309819085572996L;

  private static final Logger log = LoggerFactory.getLogger(UserView.class);

  // Campos del fomulario con información de la persona
  private SelectOneMenu cmbTypeId;

  private InputNumber txtDocument;

  private InputText txtName;

  private InputText txtLastname;

  private InputText txtPhone;

  private Calendar calBirthDate;

  /** Lista con los tipos de documento */
  private HashMap<String, String> typesId;

  // Campos del fomulario con información para la creación del usuario.
  private InputText txtEmail; // Será usuado como nombre del usuario

  private Password txtPwd;

  private SelectOneMenu cmbRol;

  private SelectBooleanButton chkEnabled;

  /** Lista con todos los roles existentes en la aplicación. */
  private List<Rol> rols;

  /** Código del rol seleccionado. */
  private String codeRolSel;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private boolean showDialog;

  // Atributos para el manejo de datos.

  /** Lista con todos los usuarios en la aplicación. */
  private List<UserDTO> data;

  /** Dto del usuario seleccionado. */
  private UserDTO selectedUser;

  /** Entidad del usuario seleccionado. */
  private User entity;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public UserView() {
    super();
  }

  @PostConstruct
  public void init() {
    typesId = TypeId.getTypesId();
    loadRols();
  }

  /**
   * Carga todos los roles de la aplicación.
   */
  private void loadRols() {
    try {
      if (rols == null) {
        rols = businessDelegatorView.getRol();
      }
    } catch (Exception e) {
      log.error("Falló la carga de los roles de la aplicación.");
      throw new RuntimeException("Falló la carga de los roles de la aplicación");
    }
  }

  /**
   * Inidica la creación de un nuevo usuario.
   */
  public void action_new() {
    action_clear();
    selectedUser = null;
    setShowDialog(true);
  }

  /**
   * Limpia todos los campos del formulario de creación de usuario.
   */
  public void action_clear() {
    entity = null;
    selectedUser = null;
    PageUtils.clearComboBox(cmbTypeId);
    PageUtils.clearTextBox(txtDocument);
    PageUtils.clearTextBox(txtName);
    PageUtils.clearTextBox(txtLastname);
    PageUtils.clearTextBox(txtPhone);
    PageUtils.clearCalendar(calBirthDate);
    PageUtils.clearTextBox(txtEmail);
    PageUtils.clear(txtPwd);
    PageUtils.clearComboBox(cmbRol);

    PageUtils.enableTextbox(txtDocument);
    PageUtils.disableComboBox(cmbTypeId);
    PageUtils.disableTextbox(txtName);
    PageUtils.disableTextbox(txtLastname);
    PageUtils.disableTextbox(txtPhone);
    PageUtils.disableCalendar(calBirthDate);
    PageUtils.disableTextbox(txtEmail);
    PageUtils.disableComponent(txtPwd);
    PageUtils.disableComboBox(cmbRol);

    PageUtils.disableButton(btnDelete);
  }
  
  public void listener_txtId() {
    try {
      Integer document = FacesUtils.checkInteger(txtDocument);
      entity = findUserByDocumentPerson(document);
    } catch (Exception e) {
      entity = null;
    }

    PageUtils.enableComboBox(cmbTypeId);
    PageUtils.enableTextbox(txtDocument);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtLastname);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableCalendar(calBirthDate);
    PageUtils.enableTextbox(txtEmail);
    PageUtils.enableComponent(txtPwd);
    PageUtils.enableComboBox(cmbRol);
    PageUtils.enableComponent(chkEnabled);

    if (entity != null) {
      PageUtils.enableButton(btnDelete);

      Person person = entity.getPerson();
      cmbTypeId.setValue(person.getDocumentType());
      txtDocument.setValue(person.getDocument());
      txtName.setValue(person.getName());
      txtLastname.setValue(person.getLastname());
      txtPhone.setValue(person.getPhone());
      calBirthDate.setValue(person.getBirthDate());
      txtEmail.setValue(person.getEmail());
      txtPwd.setValue(entity.getPassword());
      cmbRol.setValue(entity.getRol().getCodeRol());
      chkEnabled.setValue(MapperUtil.getBooleanValueFromString(entity.getEnabled()));
    }
  }
  
  /**
   * Encuentra un usuario por el documento de la persona.
   * @param documentPerson documento de la persona con la que se buscará el aprendiz.
   * @return instancia del usuario en caso de que exista.
   * @throws Exception 
   */
  private User findUserByDocumentPerson(Integer documentPerson) throws Exception {
    if (documentPerson != null) {
      List<User> listUsers = businessDelegatorView.findUserByProperty("person.document", String.valueOf(documentPerson));
      if (listUsers.isEmpty()) {
        return null;
      } else if (listUsers.size() > 1) {
        log.error("Se encontró más de un usuario con el mismo documento: " + documentPerson);
        throw new RuntimeException("Se encontró más de un usuario con el mismo documento: " + documentPerson);
      }
      return listUsers.get(0);
    }
    return null;
  }


  public void action_edit(ActionEvent evt) {
    selectedUser = (UserDTO) (evt.getComponent().getAttributes().get("selectedUser"));

    Person person = selectedUser.getPerson();
    cmbTypeId.setValue(person.getDocumentType());
    txtDocument.setValue(person.getDocument());
    txtName.setValue(person.getName());
    txtLastname.setValue(person.getLastname());
    txtPhone.setValue(person.getPhone());
    calBirthDate.setValue(person.getBirthDate());
    txtEmail.setValue(person.getEmail());
    txtPwd.setValue(selectedUser.getPassword());
    cmbRol.setValue(selectedUser.getRol().getCodeRol());
    chkEnabled.setValue(selectedUser.isEnabled());

    PageUtils.enableComboBox(cmbTypeId);
    PageUtils.enableTextbox(txtDocument);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtLastname);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableCalendar(calBirthDate);
    PageUtils.enableTextbox(txtEmail);
    PageUtils.enableComponent(txtPwd);
    PageUtils.enableComboBox(cmbRol);
    PageUtils.enableComponent(chkEnabled);

    PageUtils.enableButton(btnSave);
    PageUtils.enableButton(btnDelete);
    setShowDialog(true);
  }

  public void action_save() {
    try {
      if ((selectedUser == null) && (entity == null)) {
        action_create();
      } else {
        action_modify();
      }
      data = null;
    } catch (Exception e) {
      ZMessManager.addErrorMessage(e.getMessage());
      log.error("Falló la acción de guardado del usuario", e);
    }
  }

  public void action_create() {
    try {
      entity = new User();
      Person person = new Person();
      entity.setPerson(person);
      loadValuesSinceForm(entity);

      businessDelegatorView.saveUser(entity);
      ZMessManager.addSaveMessage(ApplicationMessages.getInstance().getMessage("user.save.success"));
      action_clear();
    } catch (Exception e) {
      entity = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de creación del usuario", e);
    }
  }

  public void action_modify() {
    try {
      if (entity == null) {
        Integer idUser = new Integer(selectedUser.getIdUser());
        entity = businessDelegatorView.getUser(idUser);
      }
      loadValuesSinceForm(entity);
      businessDelegatorView.updateUser(entity);
      ZMessManager.addEditMessage(ApplicationMessages.getInstance().getMessage("user.edit.success"));
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de modificación del usuario", e);
    }
  }

  private void loadValuesSinceForm(User user) {
    user.setUsername(FacesUtils.checkString(txtEmail));
    user.setPassword(FacesUtils.checkString(txtPwd));
    Boolean enabled = (Boolean) chkEnabled.getValue();
    user.setEnabled(enabled ? "1" : "0");
    user.setRol(new Rol(this.codeRolSel));

    Person person = user.getPerson();
    person.setDocumentType(FacesUtils.checkString(cmbTypeId));
    person.setDocument(FacesUtils.checkString(txtDocument));
    person.setName(FacesUtils.checkString(txtName));
    person.setLastname(FacesUtils.checkString(txtLastname));
    person.setPhone(FacesUtils.checkString(txtPhone));
    person.setEmail(FacesUtils.checkString(txtEmail));
    person.setBirthDate(FacesUtils.checkDate(calBirthDate));
  }

  public void action_delete_datatable(ActionEvent evt) {
    try {
      selectedUser = (UserDTO) (evt.getComponent().getAttributes().get("selectedUser"));
      Integer idUser = new Integer(selectedUser.getIdUser());
      entity = businessDelegatorView.getUser(idUser);
      delete(entity);
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de eliminación del usuario", e);
    }
  }

  public void action_delete() throws Exception {
    delete(entity);
  }

  private void delete(User user) throws Exception {
    try {
      businessDelegatorView.deleteUser(entity);
      ZMessManager.addDeleteMessage(ApplicationMessages.getInstance().getMessage("user.delete.success"));
      action_clear();
      data = null;
    } catch (Exception e) {
      log.error("Falló la acción de eliminación del aprediz", e);
      throw e;
    }
  }

  public void action_closeDialog() {
    setShowDialog(false);
    action_clear();
  }

  // Gets & Sets

  public InputText getTxtName() {
    return txtName;
  }

  public void setTxtName(InputText txtName) {
    this.txtName = txtName;
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

  public Calendar getCalBirthDate() {
    return calBirthDate;
  }

  public void setCalBirthDate(Calendar calBirthDate) {
    this.calBirthDate = calBirthDate;
  }

  public InputText getTxtEmail() {
    return txtEmail;
  }

  public void setTxtEmail(InputText txtEmail) {
    this.txtEmail = txtEmail;
  }

  public Password getTxtPwd() {
    return txtPwd;
  }

  public void setTxtPwd(Password txtPwd) {
    this.txtPwd = txtPwd;
  }

  public SelectOneMenu getCmbRol() {
    return cmbRol;
  }

  public void setCmbRol(SelectOneMenu cmbRol) {
    this.cmbRol = cmbRol;
  }

  public SelectBooleanButton getChkEnabled() {
    return chkEnabled;
  }

  public void setChkEnabled(SelectBooleanButton chkEnabled) {
    this.chkEnabled = chkEnabled;
  }

  public boolean isShowDialog() {
    return showDialog;
  }

  public void setShowDialog(boolean showDialog) {
    this.showDialog = showDialog;
  }

  public HashMap<String, String> getTypesId() {
    return typesId;
  }

  public void setTypesId(HashMap<String, String> typesId) {
    this.typesId = typesId;
  }

  public String getCodeRolSel() {
    return codeRolSel;
  }

  public void setCodeRolSel(String codeRolSel) {
    this.codeRolSel = codeRolSel;
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

  public List<UserDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataUser();
      }
      return data;
    } catch (Exception e) {
      throw new RuntimeException("Falló la carga de la información de los usuarios de la aplicación");
    }
  }

  public void setData(List<UserDTO> data) {
    this.data = data;
  }

  public UserDTO getSelectedUser() {
    return selectedUser;
  }

  public void setSelectedUser(UserDTO selectedUser) {
    this.selectedUser = selectedUser;
  }

  public List<Rol> getRols() {
    return rols;
  }

  public void setRols(List<Rol> rols) {
    this.rols = rols;
  }

  public User getEntity() {
    return entity;
  }

  public void setEntity(User entity) {
    this.entity = entity;
  }

  public IBusinessDelegatorView getBusinessDelegatorView() {
    return businessDelegatorView;
  }

  public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
    this.businessDelegatorView = businessDelegatorView;
  }
  
}
