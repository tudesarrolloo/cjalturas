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
import com.cjalturas.model.Rol;
import com.cjalturas.model.dto.RolDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class RolView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(RolView.class);

  private InputText txtRol;

  private InputText txtCodeRol;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<RolDTO> data;

  private RolDTO selectedRol;

  private Rol entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public RolView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedRol = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedRol = null;

    if (txtRol != null) {
      txtRol.setValue(null);
      txtRol.setDisabled(true);
    }

    if (txtCodeRol != null) {
      txtCodeRol.setValue(null);
      txtCodeRol.setDisabled(false);
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
      String codeRol = FacesUtils.checkString(txtCodeRol);
      entity = (codeRol != null) ? businessDelegatorView.getRol(codeRol) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtRol.setDisabled(false);
      txtCodeRol.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtRol.setValue(entity.getRol());
      txtRol.setDisabled(false);
      txtCodeRol.setValue(entity.getCodeRol());
      txtCodeRol.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedRol = (RolDTO) (evt.getComponent().getAttributes().get("selectedRol"));
    txtRol.setValue(selectedRol.getRol());
    txtRol.setDisabled(false);
    txtCodeRol.setValue(selectedRol.getCodeRol());
    txtCodeRol.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedRol == null) && (entity == null)) {
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
      entity = new Rol();

      String codeRol = FacesUtils.checkString(txtCodeRol);

      entity.setCodeRol(codeRol);
      entity.setRol(FacesUtils.checkString(txtRol));
      businessDelegatorView.saveRol(entity);
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
        String codeRol = new String(selectedRol.getCodeRol());
        entity = businessDelegatorView.getRol(codeRol);
      }

      entity.setRol(FacesUtils.checkString(txtRol));
      businessDelegatorView.updateRol(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedRol = (RolDTO) (evt.getComponent().getAttributes().get("selectedRol"));

      String codeRol = new String(selectedRol.getCodeRol());
      entity = businessDelegatorView.getRol(codeRol);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      String codeRol = FacesUtils.checkString(txtCodeRol);
      entity = businessDelegatorView.getRol(codeRol);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteRol(entity);
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

  public String action_modifyWitDTO(String codeRol, String rol) throws Exception {
    try {
      entity.setRol(FacesUtils.checkString(rol));
      businessDelegatorView.updateRol(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("RolView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtRol() {
    return txtRol;
  }

  public void setTxtRol(InputText txtRol) {
    this.txtRol = txtRol;
  }

  public InputText getTxtCodeRol() {
    return txtCodeRol;
  }

  public void setTxtCodeRol(InputText txtCodeRol) {
    this.txtCodeRol = txtCodeRol;
  }

  public List<RolDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataRol();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<RolDTO> rolDTO) {
    this.data = rolDTO;
  }

  public RolDTO getSelectedRol() {
    return selectedRol;
  }

  public void setSelectedRol(RolDTO rol) {
    this.selectedRol = rol;
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
