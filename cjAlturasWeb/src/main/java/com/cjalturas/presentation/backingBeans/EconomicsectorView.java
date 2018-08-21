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
import com.cjalturas.model.Economicsector;
import com.cjalturas.model.dto.EconomicsectorDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class EconomicsectorView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(EconomicsectorView.class);

  private InputText txtEconomicSector;

  private InputText txtIdEconomicSector;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<EconomicsectorDTO> data;

  private EconomicsectorDTO selectedEconomicsector;

  private Economicsector entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public EconomicsectorView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedEconomicsector = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedEconomicsector = null;

    if (txtEconomicSector != null) {
      txtEconomicSector.setValue(null);
      txtEconomicSector.setDisabled(true);
    }

    if (txtIdEconomicSector != null) {
      txtIdEconomicSector.setValue(null);
      txtIdEconomicSector.setDisabled(false);
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
      Integer idEconomicSector = FacesUtils.checkInteger(txtIdEconomicSector);
      entity = (idEconomicSector != null) ? businessDelegatorView.getEconomicsector(idEconomicSector) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtEconomicSector.setDisabled(false);
      txtIdEconomicSector.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtEconomicSector.setValue(entity.getEconomicSector());
      txtEconomicSector.setDisabled(false);
      txtIdEconomicSector.setValue(entity.getIdEconomicSector());
      txtIdEconomicSector.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedEconomicsector = (EconomicsectorDTO) (evt.getComponent().getAttributes().get("selectedEconomicsector"));
    txtEconomicSector.setValue(selectedEconomicsector.getEconomicSector());
    txtEconomicSector.setDisabled(false);
    txtIdEconomicSector.setValue(selectedEconomicsector.getIdEconomicSector());
    txtIdEconomicSector.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedEconomicsector == null) && (entity == null)) {
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
      entity = new Economicsector();

      Integer idEconomicSector = FacesUtils.checkInteger(txtIdEconomicSector);

      entity.setEconomicSector(FacesUtils.checkString(txtEconomicSector));
      entity.setIdEconomicSector(idEconomicSector);
      businessDelegatorView.saveEconomicsector(entity);
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
        Integer idEconomicSector = new Integer(selectedEconomicsector.getIdEconomicSector());
        entity = businessDelegatorView.getEconomicsector(idEconomicSector);
      }

      entity.setEconomicSector(FacesUtils.checkString(txtEconomicSector));
      businessDelegatorView.updateEconomicsector(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedEconomicsector = (EconomicsectorDTO) (evt.getComponent().getAttributes().get("selectedEconomicsector"));

      Integer idEconomicSector = new Integer(selectedEconomicsector.getIdEconomicSector());
      entity = businessDelegatorView.getEconomicsector(idEconomicSector);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      Integer idEconomicSector = FacesUtils.checkInteger(txtIdEconomicSector);
      entity = businessDelegatorView.getEconomicsector(idEconomicSector);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteEconomicsector(entity);
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

  public String action_modifyWitDTO(String economicSector, Integer idEconomicSector) throws Exception {
    try {
      entity.setEconomicSector(FacesUtils.checkString(economicSector));
      businessDelegatorView.updateEconomicsector(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("EconomicsectorView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtEconomicSector() {
    return txtEconomicSector;
  }

  public void setTxtEconomicSector(InputText txtEconomicSector) {
    this.txtEconomicSector = txtEconomicSector;
  }

  public InputText getTxtIdEconomicSector() {
    return txtIdEconomicSector;
  }

  public void setTxtIdEconomicSector(InputText txtIdEconomicSector) {
    this.txtIdEconomicSector = txtIdEconomicSector;
  }

  public List<EconomicsectorDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataEconomicsector();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<EconomicsectorDTO> economicsectorDTO) {
    this.data = economicsectorDTO;
  }

  public EconomicsectorDTO getSelectedEconomicsector() {
    return selectedEconomicsector;
  }

  public void setSelectedEconomicsector(EconomicsectorDTO economicsector) {
    this.selectedEconomicsector = economicsector;
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
