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

import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.model.Coach;
import com.cjalturas.model.dto.CoachDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CoachView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(CoachView.class);
  
  private InputNumber txtDocument;
  
  private SelectOneMenu lids;

  private InputText txtCharge;

  private InputText txtLicenseSst;

  private InputText txtSign;

  private InputText txtIdPerson_Person;

  private InputText txtIdCoach;

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
    typesId = new HashMap<String, String>();
    typesId.put("CC", "Cédula de ciudadanía");
    typesId.put("CE","Cédula extranjería");
    typesId.put("PP","Pasaporte");
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

    if (txtCharge != null) {
      txtCharge.setValue(null);
      txtCharge.setDisabled(true);
    }

    if (txtLicenseSst != null) {
      txtLicenseSst.setValue(null);
      txtLicenseSst.setDisabled(true);
    }

    if (txtSign != null) {
      txtSign.setValue(null);
      txtSign.setDisabled(true);
    }

    if (txtIdPerson_Person != null) {
      txtIdPerson_Person.setValue(null);
      txtIdPerson_Person.setDisabled(true);
    }

    if (txtIdCoach != null) {
      txtIdCoach.setValue(null);
      txtIdCoach.setDisabled(false);
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
      Integer idCoach = FacesUtils.checkInteger(txtIdCoach);
      entity = (idCoach != null) ? businessDelegatorView.getCoach(idCoach) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtCharge.setDisabled(false);
      txtLicenseSst.setDisabled(false);
      txtSign.setDisabled(false);
      txtIdPerson_Person.setDisabled(false);
      txtIdCoach.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtCharge.setValue(entity.getCharge());
      txtCharge.setDisabled(false);
      txtLicenseSst.setValue(entity.getLicenseSst());
      txtLicenseSst.setDisabled(false);
      txtSign.setValue(entity.getSign());
      txtSign.setDisabled(false);
      txtIdPerson_Person.setValue(entity.getPerson().getIdPerson());
      txtIdPerson_Person.setDisabled(false);
      txtIdCoach.setValue(entity.getIdCoach());
      txtIdCoach.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedCoach = (CoachDTO) (evt.getComponent().getAttributes().get("selectedCoach"));
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
