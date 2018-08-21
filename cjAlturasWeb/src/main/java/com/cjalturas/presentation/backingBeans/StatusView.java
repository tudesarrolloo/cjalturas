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
import com.cjalturas.model.Status;
import com.cjalturas.model.dto.StatusDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class StatusView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(StatusView.class);

  private InputText txtStatus;

  private InputText txtCode;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<StatusDTO> data;

  private StatusDTO selectedStatus;

  private Status entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public StatusView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedStatus = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedStatus = null;

    if (txtStatus != null) {
      txtStatus.setValue(null);
      txtStatus.setDisabled(true);
    }

    if (txtCode != null) {
      txtCode.setValue(null);
      txtCode.setDisabled(false);
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
      String code = FacesUtils.checkString(txtCode);
      entity = (code != null) ? businessDelegatorView.getStatus(code) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtStatus.setDisabled(false);
      txtCode.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtStatus.setValue(entity.getStatus());
      txtStatus.setDisabled(false);
      txtCode.setValue(entity.getCode());
      txtCode.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedStatus = (StatusDTO) (evt.getComponent().getAttributes().get("selectedStatus"));
    txtStatus.setValue(selectedStatus.getStatus());
    txtStatus.setDisabled(false);
    txtCode.setValue(selectedStatus.getCode());
    txtCode.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedStatus == null) && (entity == null)) {
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
      entity = new Status();

      String code = FacesUtils.checkString(txtCode);

      entity.setCode(code);
      entity.setStatus(FacesUtils.checkString(txtStatus));
      businessDelegatorView.saveStatus(entity);
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
        String code = new String(selectedStatus.getCode());
        entity = businessDelegatorView.getStatus(code);
      }

      entity.setStatus(FacesUtils.checkString(txtStatus));
      businessDelegatorView.updateStatus(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedStatus = (StatusDTO) (evt.getComponent().getAttributes().get("selectedStatus"));

      String code = new String(selectedStatus.getCode());
      entity = businessDelegatorView.getStatus(code);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      String code = FacesUtils.checkString(txtCode);
      entity = businessDelegatorView.getStatus(code);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteStatus(entity);
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

  public String action_modifyWitDTO(String code, String status) throws Exception {
    try {
      entity.setStatus(FacesUtils.checkString(status));
      businessDelegatorView.updateStatus(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("StatusView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtStatus() {
    return txtStatus;
  }

  public void setTxtStatus(InputText txtStatus) {
    this.txtStatus = txtStatus;
  }

  public InputText getTxtCode() {
    return txtCode;
  }

  public void setTxtCode(InputText txtCode) {
    this.txtCode = txtCode;
  }

  public List<StatusDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataStatus();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<StatusDTO> statusDTO) {
    this.data = statusDTO;
  }

  public StatusDTO getSelectedStatus() {
    return selectedStatus;
  }

  public void setSelectedStatus(StatusDTO status) {
    this.selectedStatus = status;
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
