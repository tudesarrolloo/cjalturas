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
import com.cjalturas.model.Learner;
import com.cjalturas.model.dto.LearnerDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class LearnerView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(LearnerView.class);

  private InputText txtIdEconomicSector_Economicsector;

  private InputText txtIdEnterprise_Enterprise;

  private InputText txtIdPerson_Person;

  private InputText txtIdLearner;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<LearnerDTO> data;

  private LearnerDTO selectedLearner;

  private Learner entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public LearnerView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedLearner = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedLearner = null;

    if (txtIdEconomicSector_Economicsector != null) {
      txtIdEconomicSector_Economicsector.setValue(null);
      txtIdEconomicSector_Economicsector.setDisabled(true);
    }

    if (txtIdEnterprise_Enterprise != null) {
      txtIdEnterprise_Enterprise.setValue(null);
      txtIdEnterprise_Enterprise.setDisabled(true);
    }

    if (txtIdPerson_Person != null) {
      txtIdPerson_Person.setValue(null);
      txtIdPerson_Person.setDisabled(true);
    }

    if (txtIdLearner != null) {
      txtIdLearner.setValue(null);
      txtIdLearner.setDisabled(false);
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
      Integer idLearner = FacesUtils.checkInteger(txtIdLearner);
      entity = (idLearner != null) ? businessDelegatorView.getLearner(idLearner) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtIdEconomicSector_Economicsector.setDisabled(false);
      txtIdEnterprise_Enterprise.setDisabled(false);
      txtIdPerson_Person.setDisabled(false);
      txtIdLearner.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtIdEconomicSector_Economicsector.setValue(entity.getEconomicsector().getIdEconomicSector());
      txtIdEconomicSector_Economicsector.setDisabled(false);
      txtIdEnterprise_Enterprise.setValue(entity.getEnterprise().getIdEnterprise());
      txtIdEnterprise_Enterprise.setDisabled(false);
      txtIdPerson_Person.setValue(entity.getPerson().getIdPerson());
      txtIdPerson_Person.setDisabled(false);
      txtIdLearner.setValue(entity.getIdLearner());
      txtIdLearner.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedLearner = (LearnerDTO) (evt.getComponent().getAttributes().get("selectedLearner"));
    txtIdEconomicSector_Economicsector.setValue(selectedLearner.getIdEconomicSector_Economicsector());
    txtIdEconomicSector_Economicsector.setDisabled(false);
    txtIdEnterprise_Enterprise.setValue(selectedLearner.getIdEnterprise_Enterprise());
    txtIdEnterprise_Enterprise.setDisabled(false);
    txtIdPerson_Person.setValue(selectedLearner.getIdPerson_Person());
    txtIdPerson_Person.setDisabled(false);
    txtIdLearner.setValue(selectedLearner.getIdLearner());
    txtIdLearner.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedLearner == null) && (entity == null)) {
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
      entity = new Learner();

      Integer idLearner = FacesUtils.checkInteger(txtIdLearner);

      entity.setIdLearner(idLearner);
      entity.setEconomicsector((FacesUtils.checkInteger(txtIdEconomicSector_Economicsector) != null)
          ? businessDelegatorView.getEconomicsector(FacesUtils.checkInteger(txtIdEconomicSector_Economicsector))
          : null);
      entity.setEnterprise((FacesUtils.checkInteger(txtIdEnterprise_Enterprise) != null)
          ? businessDelegatorView.getEnterprise(FacesUtils.checkInteger(txtIdEnterprise_Enterprise))
          : null);
      entity.setPerson(
          (FacesUtils.checkInteger(txtIdPerson_Person) != null) ? businessDelegatorView.getPerson(FacesUtils.checkInteger(txtIdPerson_Person)) : null);
      businessDelegatorView.saveLearner(entity);
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
        Integer idLearner = new Integer(selectedLearner.getIdLearner());
        entity = businessDelegatorView.getLearner(idLearner);
      }

      entity.setEconomicsector((FacesUtils.checkInteger(txtIdEconomicSector_Economicsector) != null)
          ? businessDelegatorView.getEconomicsector(FacesUtils.checkInteger(txtIdEconomicSector_Economicsector))
          : null);
      entity.setEnterprise((FacesUtils.checkInteger(txtIdEnterprise_Enterprise) != null)
          ? businessDelegatorView.getEnterprise(FacesUtils.checkInteger(txtIdEnterprise_Enterprise))
          : null);
      entity.setPerson(
          (FacesUtils.checkInteger(txtIdPerson_Person) != null) ? businessDelegatorView.getPerson(FacesUtils.checkInteger(txtIdPerson_Person)) : null);
      businessDelegatorView.updateLearner(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedLearner = (LearnerDTO) (evt.getComponent().getAttributes().get("selectedLearner"));

      Integer idLearner = new Integer(selectedLearner.getIdLearner());
      entity = businessDelegatorView.getLearner(idLearner);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      Integer idLearner = FacesUtils.checkInteger(txtIdLearner);
      entity = businessDelegatorView.getLearner(idLearner);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteLearner(entity);
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

  public String action_modifyWitDTO(Integer idLearner, Integer idEconomicSector_Economicsector, Integer idEnterprise_Enterprise, Integer idPerson_Person)
      throws Exception {
    try {
      businessDelegatorView.updateLearner(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("LearnerView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtIdEconomicSector_Economicsector() {
    return txtIdEconomicSector_Economicsector;
  }

  public void setTxtIdEconomicSector_Economicsector(InputText txtIdEconomicSector_Economicsector) {
    this.txtIdEconomicSector_Economicsector = txtIdEconomicSector_Economicsector;
  }

  public InputText getTxtIdEnterprise_Enterprise() {
    return txtIdEnterprise_Enterprise;
  }

  public void setTxtIdEnterprise_Enterprise(InputText txtIdEnterprise_Enterprise) {
    this.txtIdEnterprise_Enterprise = txtIdEnterprise_Enterprise;
  }

  public InputText getTxtIdPerson_Person() {
    return txtIdPerson_Person;
  }

  public void setTxtIdPerson_Person(InputText txtIdPerson_Person) {
    this.txtIdPerson_Person = txtIdPerson_Person;
  }

  public InputText getTxtIdLearner() {
    return txtIdLearner;
  }

  public void setTxtIdLearner(InputText txtIdLearner) {
    this.txtIdLearner = txtIdLearner;
  }

  public List<LearnerDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataLearner();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<LearnerDTO> learnerDTO) {
    this.data = learnerDTO;
  }

  public LearnerDTO getSelectedLearner() {
    return selectedLearner;
  }

  public void setSelectedLearner(LearnerDTO learner) {
    this.selectedLearner = learner;
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
