package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.model.Group;
import com.cjalturas.model.dto.GroupDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class GroupView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(GroupView.class);

  private InputText txtObservations;

  private InputText txtIdCoach_Coach;

  private InputText txtIdCourse_Course;

  private InputText txtIdGroup;

  private Calendar txtDateStart;

  private CommandButton btnSave;

  private CommandButton btnModify;

  private CommandButton btnDelete;

  private CommandButton btnClear;

  private List<GroupDTO> data;

  private GroupDTO selectedGroup;

  private Group entity;

  private boolean showDialog;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public GroupView() {
    super();
  }

  public String action_new() {
    action_clear();
    selectedGroup = null;
    setShowDialog(true);

    return "";
  }

  public String action_clear() {
    entity = null;
    selectedGroup = null;

    if (txtObservations != null) {
      txtObservations.setValue(null);
      txtObservations.setDisabled(true);
    }

    if (txtIdCoach_Coach != null) {
      txtIdCoach_Coach.setValue(null);
      txtIdCoach_Coach.setDisabled(true);
    }

    if (txtIdCourse_Course != null) {
      txtIdCourse_Course.setValue(null);
      txtIdCourse_Course.setDisabled(true);
    }

    if (txtDateStart != null) {
      txtDateStart.setValue(null);
      txtDateStart.setDisabled(true);
    }

    if (txtIdGroup != null) {
      txtIdGroup.setValue(null);
      txtIdGroup.setDisabled(false);
    }

    if (btnSave != null) {
      btnSave.setDisabled(true);
    }

    if (btnDelete != null) {
      btnDelete.setDisabled(true);
    }

    return "";
  }

  public void listener_txtDateStart() {
    Date inputDate = (Date) txtDateStart.getValue();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
  }

  public void listener_txtId() {
    try {
      Integer idGroup = FacesUtils.checkInteger(txtIdGroup);
      entity = (idGroup != null) ? businessDelegatorView.getGroup(idGroup) : null;
    } catch (Exception e) {
      entity = null;
    }

    if (entity == null) {
      txtObservations.setDisabled(false);
      txtIdCoach_Coach.setDisabled(false);
      txtIdCourse_Course.setDisabled(false);
      txtDateStart.setDisabled(false);
      txtIdGroup.setDisabled(false);
      btnSave.setDisabled(false);
    } else {
      txtDateStart.setValue(entity.getDateStart());
      txtDateStart.setDisabled(false);
      txtObservations.setValue(entity.getObservations());
      txtObservations.setDisabled(false);
      txtIdCoach_Coach.setValue(entity.getCoach().getIdCoach());
      txtIdCoach_Coach.setDisabled(false);
      txtIdCourse_Course.setValue(entity.getCourse().getIdCourse());
      txtIdCourse_Course.setDisabled(false);
      txtIdGroup.setValue(entity.getIdGroup());
      txtIdGroup.setDisabled(true);
      btnSave.setDisabled(false);

      if (btnDelete != null) {
        btnDelete.setDisabled(false);
      }
    }
  }

  public String action_edit(ActionEvent evt) {
    selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));
    txtDateStart.setValue(selectedGroup.getDateStart());
    txtDateStart.setDisabled(false);
    txtObservations.setValue(selectedGroup.getObservations());
    txtObservations.setDisabled(false);
    txtIdCoach_Coach.setValue(selectedGroup.getIdCoach_Coach());
    txtIdCoach_Coach.setDisabled(false);
    txtIdCourse_Course.setValue(selectedGroup.getIdCourse_Course());
    txtIdCourse_Course.setDisabled(false);
    txtIdGroup.setValue(selectedGroup.getIdGroup());
    txtIdGroup.setDisabled(true);
    btnSave.setDisabled(false);
    setShowDialog(true);

    return "";
  }

  public String action_save() {
    try {
      if ((selectedGroup == null) && (entity == null)) {
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
      entity = new Group();

      Integer idGroup = FacesUtils.checkInteger(txtIdGroup);

      entity.setDateStart(FacesUtils.checkDate(txtDateStart));
      entity.setIdGroup(idGroup);
      entity.setObservations(FacesUtils.checkString(txtObservations));
      entity.setCoach((FacesUtils.checkInteger(txtIdCoach_Coach) != null) ? businessDelegatorView.getCoach(FacesUtils.checkInteger(txtIdCoach_Coach)) : null);
      entity.setCourse(
          (FacesUtils.checkInteger(txtIdCourse_Course) != null) ? businessDelegatorView.getCourse(FacesUtils.checkInteger(txtIdCourse_Course)) : null);
      businessDelegatorView.saveGroup(entity);
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
        Integer idGroup = new Integer(selectedGroup.getIdGroup());
        entity = businessDelegatorView.getGroup(idGroup);
      }

      entity.setDateStart(FacesUtils.checkDate(txtDateStart));
      entity.setObservations(FacesUtils.checkString(txtObservations));
      entity.setCoach((FacesUtils.checkInteger(txtIdCoach_Coach) != null) ? businessDelegatorView.getCoach(FacesUtils.checkInteger(txtIdCoach_Coach)) : null);
      entity.setCourse(
          (FacesUtils.checkInteger(txtIdCourse_Course) != null) ? businessDelegatorView.getCourse(FacesUtils.checkInteger(txtIdCourse_Course)) : null);
      businessDelegatorView.updateGroup(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      data = null;
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_datatable(ActionEvent evt) {
    try {
      selectedGroup = (GroupDTO) (evt.getComponent().getAttributes().get("selectedGroup"));

      Integer idGroup = new Integer(selectedGroup.getIdGroup());
      entity = businessDelegatorView.getGroup(idGroup);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public String action_delete_master() {
    try {
      Integer idGroup = FacesUtils.checkInteger(txtIdGroup);
      entity = businessDelegatorView.getGroup(idGroup);
      action_delete();
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
    }

    return "";
  }

  public void action_delete() throws Exception {
    try {
      businessDelegatorView.deleteGroup(entity);
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

  public String action_modifyWitDTO(Date dateStart, Integer idGroup, String observations, Integer idCoach_Coach, Integer idCourse_Course) throws Exception {
    try {
      entity.setDateStart(FacesUtils.checkDate(dateStart));
      entity.setObservations(FacesUtils.checkString(observations));
      businessDelegatorView.updateGroup(entity);
      FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
    } catch (Exception e) {
      // renderManager.getOnDemandRenderer("GroupView").requestRender();
      FacesUtils.addErrorMessage(e.getMessage());
      throw e;
    }

    return "";
  }

  public InputText getTxtObservations() {
    return txtObservations;
  }

  public void setTxtObservations(InputText txtObservations) {
    this.txtObservations = txtObservations;
  }

  public InputText getTxtIdCoach_Coach() {
    return txtIdCoach_Coach;
  }

  public void setTxtIdCoach_Coach(InputText txtIdCoach_Coach) {
    this.txtIdCoach_Coach = txtIdCoach_Coach;
  }

  public InputText getTxtIdCourse_Course() {
    return txtIdCourse_Course;
  }

  public void setTxtIdCourse_Course(InputText txtIdCourse_Course) {
    this.txtIdCourse_Course = txtIdCourse_Course;
  }

  public Calendar getTxtDateStart() {
    return txtDateStart;
  }

  public void setTxtDateStart(Calendar txtDateStart) {
    this.txtDateStart = txtDateStart;
  }

  public InputText getTxtIdGroup() {
    return txtIdGroup;
  }

  public void setTxtIdGroup(InputText txtIdGroup) {
    this.txtIdGroup = txtIdGroup;
  }

  public List<GroupDTO> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getDataGroup();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return data;
  }

  public void setData(List<GroupDTO> groupDTO) {
    this.data = groupDTO;
  }

  public GroupDTO getSelectedGroup() {
    return selectedGroup;
  }

  public void setSelectedGroup(GroupDTO group) {
    this.selectedGroup = group;
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
