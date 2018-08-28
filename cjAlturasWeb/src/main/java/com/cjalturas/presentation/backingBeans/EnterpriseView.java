package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Enterprise;
import com.cjalturas.model.dto.EnterpriseDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;


/**
 * Bean de la vista de lista y edición de empresas.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class EnterpriseView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(EnterpriseView.class);

  private InputText txtNit;

  private InputText txtName;

  private InputText txtPhone;

  private InputText txtContactName;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<EnterpriseDTO> data;

  private EnterpriseDTO selectedEnterprise;

  private Enterprise entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public EnterpriseView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedEnterprise = null;
    setShowDialog(true);
    return "";
  }

  public String action_clear() {
    entity = null;
    selectedEnterprise = null;
    PageUtils.clearTextBox(txtNit);
    PageUtils.clearTextBox(txtName);
    PageUtils.clearTextBox(txtContactName);
    PageUtils.clearTextBox(txtPhone);

    PageUtils.enableTextbox(txtNit);
    PageUtils.disableTextbox(txtName);
    PageUtils.disableTextbox(txtContactName);
    PageUtils.disableTextbox(txtPhone);
    return "";
  }

  public void listener_txtNit() {
    try {
      String nit = FacesUtils.checkString(txtNit);
      entity = findEnterpriseByNit(nit);
    } catch (Exception e) {
      entity = null;
    }

    PageUtils.enableTextbox(txtNit);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableTextbox(txtContactName);
    PageUtils.enableButton(btnSave);
    if (entity != null) {
      PageUtils.disableTextbox(txtNit);
      txtNit.setValue(entity.getNit());
      txtName.setValue(entity.getName());
      txtPhone.setValue(entity.getPhone());
      txtContactName.setValue(entity.getContactName());
      
      PageUtils.enableButton(btnDelete);
    }
  }

  private Enterprise findEnterpriseByNit(String nit) throws Exception {
    if (nit != null) {
      List<Enterprise> listEnterprises = businessDelegatorView.findEnterpriseByProperty("nit", nit);
      if (listEnterprises.isEmpty()) {
        return null;
      } else if (listEnterprises.size() > 1) {
        log.error("Se encontró más de una empresa con el mismo nit: " + nit);
        throw new RuntimeException("Se encontró más de una empresa con el mismo nit: " + nit);
      }
      return listEnterprises.get(0);
    }
    return null;
  }

  public String action_edit(ActionEvent evt) {
    selectedEnterprise = (EnterpriseDTO) (evt.getComponent().getAttributes().get("selectedEnterprise"));
    txtNit.setValue(selectedEnterprise.getNit());
    txtName.setValue(selectedEnterprise.getName());
    txtPhone.setValue(selectedEnterprise.getPhone());
    txtContactName.setValue(selectedEnterprise.getContactName());
    PageUtils.enableTextbox(txtNit);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableTextbox(txtContactName);
    PageUtils.enableButton(btnSave);
    PageUtils.enableButton(btnDelete);
    setShowDialog(true);
    return "";
  }

  public String action_save() {
    try {
      if ((selectedEnterprise == null) && (entity == null)) {
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
      entity = new Enterprise();
      entity.setNit(FacesUtils.checkString(txtNit));
      entity.setName(FacesUtils.checkString(txtName));
      entity.setPhone(FacesUtils.checkString(txtPhone));
      entity.setContactName(FacesUtils.checkString(txtContactName));
      businessDelegatorView.saveEnterprise(entity);
      ZMessManager.addSaveMessage(ApplicationMessages.getInstance().getMessage("enterprise.save.success"));
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
        Integer idEnterprise = new Integer(selectedEnterprise.getIdEnterprise());
        entity = businessDelegatorView.getEnterprise(idEnterprise);
      }
      entity.setNit(FacesUtils.checkString(txtNit));
      entity.setName(FacesUtils.checkString(txtName));
      entity.setPhone(FacesUtils.checkString(txtPhone));
      entity.setContactName(FacesUtils.checkString(txtContactName));
      businessDelegatorView.updateEnterprise(entity);
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
      selectedEnterprise = (EnterpriseDTO) (evt.getComponent().getAttributes().get("selectedEnterprise"));
      Integer idEnterprise = new Integer(selectedEnterprise.getIdEnterprise());
      entity = businessDelegatorView.getEnterprise(idEnterprise);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de eliminación del curso", e);
    }
    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteEnterprise(entity);
      ZMessManager.addDeleteMessage(ApplicationMessages.getInstance().getMessage("course.delete.success"));
      action_clear();
      data = null;
    } catch (Exception e) {
      log.error("Falló la acción de eliminación del curso", e);
      throw e;
    }
  }

  public String action_closeDialog() {
    setShowDialog(false);
    action_clear();

    return "";
  }

  public InputText getTxtContactName() {
    return txtContactName;
  }

  public void setTxtContactName(InputText txtContactName) {
    this.txtContactName = txtContactName;
  }

  public InputText getTxtName() {
    return txtName;
  }

  public void setTxtName(InputText txtName) {
    this.txtName = txtName;
  }

  public InputText getTxtNit() {
    return txtNit;
  }

  public void setTxtNit(InputText txtNit) {
    this.txtNit = txtNit;
  }

  public InputText getTxtPhone() {
    return txtPhone;
  }

  public void setTxtPhone(InputText txtPhone) {
    this.txtPhone = txtPhone;
  }

  public List<EnterpriseDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataEnterprise();
      }
    } catch (Exception e) {
      log.error("Falló obteniendo los datos de los cursos actuales", e);
      ZMessManager.addErrorMessage(e.getMessage());
    }
    return data;
  }

  public void setData(List<EnterpriseDTO> enterpriseDTO) {
    this.data = enterpriseDTO;
  }

  public EnterpriseDTO getSelectedEnterprise() {
    return selectedEnterprise;
  }

  public void setSelectedEnterprise(EnterpriseDTO enterprise) {
    this.selectedEnterprise = enterprise;
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
