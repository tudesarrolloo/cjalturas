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
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Economicsector;
import com.cjalturas.model.dto.EconomicsectorDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;


/**
 * Bean de la vista de lista y edición de sectores económicos.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class EconomicsectorView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(EconomicsectorView.class);

  private InputText txtEconomicSector;

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
    PageUtils.clearTextBox(txtEconomicSector);
    PageUtils.disableButton(btnDelete);
    return "";
  }

  public String action_edit(ActionEvent evt) {
    selectedEconomicsector = (EconomicsectorDTO) (evt.getComponent().getAttributes().get("selectedEconomicsector"));
    txtEconomicSector.setValue(selectedEconomicsector.getEconomicSector());
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
      log.error("Falló la acción de guardado del sector económico", e);
    }
    return "";
  }

  public String action_create() {
    try {
      entity = new Economicsector();
      entity.setEconomicSector(FacesUtils.checkString(txtEconomicSector));
      businessDelegatorView.saveEconomicsector(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
      action_clear();
    } catch (Exception e) {
      entity = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de creación del sector económico", e);
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
      ZMessManager.addEditMessage(ApplicationMessages.getInstance().getMessage("economicsector.edit.success"));
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de modificación del sector económico", e);
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
      log.error("Falló la acción de eliminación del sector economico", e);
    }
    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteEconomicsector(entity);
      ZMessManager.addDeleteMessage(ApplicationMessages.getInstance().getMessage("course.delete.success"));
      action_clear();
      data = null;
    } catch (Exception e) {
      log.error("Falló la acción de eliminación del sector económico", e);
      throw e;
    }
  }

  public String action_closeDialog() {
    setShowDialog(false);
    action_clear();
    return "";
  }

  public InputText getTxtEconomicSector() {
    return txtEconomicSector;
  }

  public void setTxtEconomicSector(InputText txtEconomicSector) {
    this.txtEconomicSector = txtEconomicSector;
  }

  public List<EconomicsectorDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataEconomicsector();
      }
    } catch (Exception e) {
      log.error("Falló obteniendo los datos de los sectores económicos actuales", e);
      ZMessManager.addErrorMessage(e.getMessage());
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
