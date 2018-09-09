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
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Economicsector;
import com.cjalturas.model.Enterprise;
import com.cjalturas.model.Learner;
import com.cjalturas.model.Person;
import com.cjalturas.model.TypeId;
import com.cjalturas.model.dto.LearnerDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;


/**
 * Bean de la vista de lista y edición de aprendices.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class LearnerView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(LearnerView.class);
  
  private SelectOneMenu cmbTypeId;

  private InputNumber txtDocument;

  private InputText txtName;

  private InputText txtLastname;

  private InputText txtPhone;

  private InputText txtEmail;
  
  private SelectOneMenu cmbEconomicSector;
  
  private SelectOneMenu cmbEnterprise;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<LearnerDTO> data;

  private LearnerDTO selectedLearner;

  private Learner entity;

  private boolean showDialog;
  
  private HashMap<String, String> typesId;
  
  private Enterprise enterpriseSel;
  
  private Economicsector economicSectorSel;
  
  private List<Economicsector> economicSectors;
  
  private List<Enterprise> enterprises;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public LearnerView() {
    super();
  }
  
  @PostConstruct
  public void init() {
    typesId = TypeId.getTypesId();
    loadEconomicsSector();
    loadEnterprises();
  }

  private void loadEconomicsSector() {
    try {
      if (economicSectors == null) {
        economicSectors = businessDelegatorView.getEconomicsector();
      }
    } catch (Exception e) {
      log.error("Falló la carga de los sectores económicos.");
      throw new RuntimeException("Falló la carga de los sectores económicos.");
    }
  }
  
  private void loadEnterprises() {
    try {
      if (enterprises == null) {
        enterprises = businessDelegatorView.getEnterprise();
      }
    } catch (Exception e) {
      log.error("Falló la carga de las empresas.");
      throw new RuntimeException("Falló la carga de las empresas.");
    }
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
    PageUtils.clearTextBox(txtDocument);
    PageUtils.clearComboBox(cmbTypeId);
    PageUtils.clearTextBox(txtName);
    PageUtils.clearTextBox(txtLastname);
    PageUtils.clearTextBox(txtPhone);
    PageUtils.clearTextBox(txtEmail);
    PageUtils.clearComboBox(cmbEconomicSector);
    PageUtils.clearComboBox(cmbEnterprise);

    PageUtils.enableTextbox(txtDocument);
    PageUtils.disableComboBox(cmbTypeId);
    PageUtils.disableTextbox(txtName);
    PageUtils.disableTextbox(txtLastname);
    PageUtils.disableTextbox(txtPhone);
    PageUtils.disableTextbox(txtEmail);
    PageUtils.disableComboBox(cmbEconomicSector);
    PageUtils.disableComboBox(cmbEnterprise);

    PageUtils.disableButton(btnDelete);
    return "";
  }

  public void listener_txtId() {
    try {
      Integer document = FacesUtils.checkInteger(txtDocument);
      entity = findLearnerByDocumentPerson(document); 
    } catch (Exception e) {
      entity = null;
    }
    
    PageUtils.enableTextbox(txtDocument);
    PageUtils.enableComboBox(cmbTypeId);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtLastname);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableTextbox(txtEmail);
    PageUtils.enableComboBox(cmbEconomicSector);
    PageUtils.enableComboBox(cmbEnterprise);

    if (entity != null) {
      PageUtils.disableTextbox(txtDocument);
      PageUtils.enableButton(btnDelete);
      
      Person person = entity.getPerson();
      txtDocument.setValue(person.getDocument());
      cmbTypeId.setValue(person.getDocumentType());
      txtName.setValue(person.getName());
      txtLastname.setValue(person.getLastname());
      txtPhone.setValue(person.getPhone());
      txtEmail.setValue(person.getEmail());
      cmbEconomicSector.setValue(person.getDocumentType());
      cmbEnterprise.setValue(person.getDocumentType());
    } 
  }
  
  /**
   * Encuentra un aprendiz por el documento de la persona.
   * @param documentPerson documento de la persona con la que se buscará el aprendiz.
   * @return instancia del aprendiz en caso de que exista.
   * @throws Exception
   */
  private Learner findLearnerByDocumentPerson(Integer documentPerson) throws Exception {
    if (documentPerson != null) {
      List<Learner> listPersons = businessDelegatorView.findLearnerByProperty("person.document", String.valueOf(documentPerson));
      if (listPersons.isEmpty()) {
        return null;
      } else if (listPersons.size() > 1) {
        log.error("Se encontró más de un aprendiz con el mismo documento: " + documentPerson);
        throw new RuntimeException("Se encontró más de un aprendiz con el mismo documento: " + documentPerson);
      }
      return listPersons.get(0);
    }
    return null;
  }

  public String action_edit(ActionEvent evt) {
    selectedLearner = (LearnerDTO) (evt.getComponent().getAttributes().get("selectedLearner"));
    
    Person person = selectedLearner.getPerson();
    txtDocument.setValue(person.getDocument());
    cmbTypeId.setValue(person.getDocumentType());
    txtName.setValue(person.getName());
    txtLastname.setValue(person.getLastname());
    txtPhone.setValue(person.getPhone());
    txtEmail.setValue(person.getEmail());
    cmbEconomicSector.setValue(selectedLearner.getEconomicSector().getEconomicSector());
    cmbEnterprise.setValue(selectedLearner.getEnterprise().getName());
    
    PageUtils.enableTextbox(txtDocument);
    PageUtils.enableComboBox(cmbTypeId);
    PageUtils.enableTextbox(txtName);
    PageUtils.enableTextbox(txtLastname);
    PageUtils.enableTextbox(txtPhone);
    PageUtils.enableTextbox(txtEmail);
    PageUtils.enableComboBox(cmbEconomicSector);
    PageUtils.enableComboBox(cmbEnterprise);

    PageUtils.enableButton(btnSave);
    PageUtils.enableButton(btnDelete);
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
      ZMessManager.addErrorMessage(e.getMessage());
      log.error("Falló la acción de guardado del aprendiz", e);
    }
    return "";
  }

  public String action_create() {
    try {
      Person person = new Person();
      person.setDocument(FacesUtils.checkString(txtDocument));
      person.setDocumentType(FacesUtils.checkString(cmbTypeId));
      person.setName(FacesUtils.checkString(txtName));
      person.setLastname(FacesUtils.checkString(txtLastname));
      person.setPhone(FacesUtils.checkString(txtPhone));
      person.setEmail(FacesUtils.checkString(txtEmail));
      
      entity = new Learner();
      entity.setEconomicsector((Economicsector) cmbEconomicSector.getValue());
      entity.setEnterprise(enterpriseSel);
      entity.setPerson(person);
      
      businessDelegatorView.saveLearner(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
      ZMessManager.addSaveMessage(ApplicationMessages.getInstance().getMessage("learner.edit.success"));
      action_clear();
    } catch (Exception e) {
      entity = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de creación del aprendiz", e);
    }
    return "";
  }

  public String action_modify() {
    try {
      if (entity == null) {
        Integer idLearner = new Integer(selectedLearner.getIdLearner());
        entity = businessDelegatorView.getLearner(idLearner);
      }
      
      Person person = entity.getPerson();
      person.setDocument(FacesUtils.checkString(txtDocument));
      person.setDocumentType(FacesUtils.checkString(cmbTypeId));
      person.setName(FacesUtils.checkString(txtName));
      person.setLastname(FacesUtils.checkString(txtLastname));
      person.setPhone(FacesUtils.checkString(txtPhone));
      person.setEmail(FacesUtils.checkString(txtEmail));

      entity.setEconomicsector(economicSectorSel);
      entity.setEnterprise(enterpriseSel);
      entity.setPerson(person);
      businessDelegatorView.updateLearner(entity);
      ZMessManager.addEditMessage(ApplicationMessages.getInstance().getMessage("learner.edit.success"));
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de modificación del aprendiz", e);
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
      log.error("Falló la acción de eliminación del aprendiz", e);
    }
    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteLearner(entity);
      ZMessManager.addDeleteMessage(ApplicationMessages.getInstance().getMessage("learner.delete.success"));
      action_clear();
      data = null;
    } catch (Exception e) {
      log.error("Falló la acción de eliminación del aprediz", e);
      throw e;
    }
  }

  public String action_closeDialog() {
    setShowDialog(false);
    action_clear();
    return "";
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

  public InputText getTxtName() {
    return txtName;
  }

  public void setTxtName(InputText txtName) {
    this.txtName = txtName;
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

  public InputText getTxtEmail() {
    return txtEmail;
  }

  public void setTxtEmail(InputText txtEmail) {
    this.txtEmail = txtEmail;
  }

  public SelectOneMenu getCmbEconomicSector() {
    return cmbEconomicSector;
  }

  public void setCmbEconomicSector(SelectOneMenu cmbEconomicSector) {
    this.cmbEconomicSector = cmbEconomicSector;
  }

  public SelectOneMenu getCmbEnterprise() {
    return cmbEnterprise;
  }

  public void setCmbEnterprise(SelectOneMenu cmbEnterprise) {
    this.cmbEnterprise = cmbEnterprise;
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

  public LearnerDTO getSelectedLearner() {
    return selectedLearner;
  }

  public void setSelectedLearner(LearnerDTO selectedLearner) {
    this.selectedLearner = selectedLearner;
  }

  public Learner getEntity() {
    return entity;
  }

  public void setEntity(Learner entity) {
    this.entity = entity;
  }

  public HashMap<String, String> getTypesId() {
    return typesId;
  }

  public void setTypesId(HashMap<String, String> typesId) {
    this.typesId = typesId;
  }

  public Enterprise getEnterpriseSel() {
    return enterpriseSel;
  }

  public void setEnterpriseSel(Enterprise enterpriseSel) {
    this.enterpriseSel = enterpriseSel;
  }

  public Economicsector getEconomicSectorSel() {
    return economicSectorSel;
  }

  public void setEconomicSectorSel(Economicsector economicSectorSel) {
    this.economicSectorSel = economicSectorSel;
  }

  public List<Economicsector> getEconomicSectors() {
    return economicSectors;
  }

  public void setEconomicSectors(List<Economicsector> economicSectors) {
    this.economicSectors = economicSectors;
  }

  public List<Enterprise> getEnterprises() {
    return enterprises;
  }

  public void setEnterprises(List<Enterprise> enterprises) {
    this.enterprises = enterprises;
  }
  
}
