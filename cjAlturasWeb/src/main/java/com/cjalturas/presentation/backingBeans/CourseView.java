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
import com.cjalturas.model.Course;
import com.cjalturas.model.dto.CourseDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FacesUtils;
import com.cjalturas.utilities.PageUtils;


/**
 * Bean de la vista de lista y edición de cursos.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class CourseView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CourseView.class);
    private InputText txtCourse;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<CourseDTO> data;
    private CourseDTO selectedCourse;
    private Course entity;
    private boolean showDialog;
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public CourseView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedCourse = null;
        setShowDialog(true);
        return "";
    }

    public String action_clear() {
        entity = null;
        selectedCourse = null;
        PageUtils.clearTextBox(txtCourse);
        PageUtils.disableButton(btnDelete);
        return "";
    }

    public String action_edit(ActionEvent evt) {
        selectedCourse = (CourseDTO) (evt.getComponent().getAttributes().get("selectedCourse"));
        txtCourse.setValue(selectedCourse.getCourse());
        setShowDialog(true);
        return "";
    }

    public String action_save() {
        try {
            if ((selectedCourse == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }
            data = null;
        } catch (Exception e) {
        	ZMessManager.addErrorMessage(e.getMessage());
        	log.error("Falló la acción de guardado del curso", e);
        }
        return "";
    }

    public String action_create() {
        try {
            entity = new Course();
            entity.setCourse(FacesUtils.checkString(txtCourse));
            businessDelegatorView.saveCourse(entity);
            ZMessManager.addSaveMessage(ApplicationMessages.getInstance().getMessage("course.save.success"));
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
                Integer idCourse = new Integer(selectedCourse.getIdCourse());
                entity = businessDelegatorView.getCourse(idCourse);
            }
            entity.setCourse(FacesUtils.checkString(txtCourse));
            businessDelegatorView.updateCourse(entity);
            ZMessManager.addEditMessage(ApplicationMessages.getInstance().getMessage("course.edit.success"));
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
            log.error("Falló la acción de modificación del curso", e);
        }
        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCourse = (CourseDTO) (evt.getComponent().getAttributes().get("selectedCourse"));
            Integer idCourse = new Integer(selectedCourse.getIdCourse());
            entity = businessDelegatorView.getCourse(idCourse);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
            log.error("Falló la acción de eliminación del curso", e);
        }
        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCourse(entity);
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

    public InputText getTxtCourse() {
        return txtCourse;
    }

    public void setTxtCourse(InputText txtCourse) {
        this.txtCourse = txtCourse;
    }

	public List<CourseDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCourse();
            }
        } catch (Exception e) {
        	  log.error("Falló obteniendo los datos de los cursos actuales", e);
            ZMessManager.addErrorMessage(e.getMessage());
        }
        return data;
    }

    public void setData(List<CourseDTO> courseDTO) {
        this.data = courseDTO;
    }

    public CourseDTO getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(CourseDTO course) {
        this.selectedCourse = course;
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

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
    
}
