package com.cjalturas.presentation.backingBeans;

import com.cjalturas.exceptions.*;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.*;
import com.cjalturas.model.dto.CourseDTO;

import com.cjalturas.presentation.businessDelegate.*;

import com.cjalturas.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class CourseView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CourseView.class);
    private InputText txtCourse;
//    private InputText txtIdCourse;
    private OutputLabel lblIdCourse;
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

//    public void listener_txtId() {
//        try {
//            Integer idCourse = FacesUtils.checkInteger(txtIdCourse);
//            entity = (idCourse != null)
//                ? businessDelegatorView.getCourse(idCourse) : null;
//        } catch (Exception e) {
//            entity = null;
//        }
//
//        if (entity == null) {
//            txtCourse.setDisabled(false);
//            txtIdCourse.setDisabled(false);
//            btnSave.setDisabled(false);
//        } else {
//            txtCourse.setValue(entity.getCourse());
//            txtCourse.setDisabled(false);
//            txtIdCourse.setValue(entity.getIdCourse());
//            txtIdCourse.setDisabled(true);
//            btnSave.setDisabled(false);
//
//            if (btnDelete != null) {
//                btnDelete.setDisabled(false);
//            }
//        }
//    }

    public String action_edit(ActionEvent evt) {
        selectedCourse = (CourseDTO) (evt.getComponent().getAttributes()
                                         .get("selectedCourse"));
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
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {

    	
    	
        try {
            entity = new Course();

//            Integer idCourse = FacesUtils.checkInteger(txtIdCourse);

            entity.setCourse(FacesUtils.checkString(txtCourse));
//            entity.setIdCourse(idCourse);
            businessDelegatorView.saveCourse(entity);
            ZMessManager.addSaveMessage(ApplicationMessages.getInstance().getMessage("course.save.success"));
//            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
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
                Integer idCourse = new Integer(selectedCourse.getIdCourse());
                entity = businessDelegatorView.getCourse(idCourse);
            }

            entity.setCourse(FacesUtils.checkString(txtCourse));
            businessDelegatorView.updateCourse(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedCourse = (CourseDTO) (evt.getComponent().getAttributes()
                                             .get("selectedCourse"));

            Integer idCourse = new Integer(selectedCourse.getIdCourse());
            entity = businessDelegatorView.getCourse(idCourse);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

//    public String action_delete_master() {
//        try {
//            Integer idCourse = FacesUtils.checkInteger(txtIdCourse);
//            entity = businessDelegatorView.getCourse(idCourse);
//            action_delete();
//        } catch (Exception e) {
//            FacesUtils.addErrorMessage(e.getMessage());
//        }
//
//        return "";
//    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteCourse(entity);
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

    public String action_modifyWitDTO(String course, Integer idCourse)
        throws Exception {
        try {
            entity.setCourse(FacesUtils.checkString(course));
            businessDelegatorView.updateCourse(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("CourseView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCourse() {
        return txtCourse;
    }

    public void setTxtCourse(InputText txtCourse) {
        this.txtCourse = txtCourse;
    }

//    public InputText getTxtIdCourse() {
//        return txtIdCourse;
//    }
//
//    public void setTxtIdCourse(InputText txtIdCourse) {
//        this.txtIdCourse = txtIdCourse;
//    }
    
    

    public OutputLabel getLblIdCourse() {
		return lblIdCourse;
	}

	public void setLblIdCourse(OutputLabel lblIdCourse) {
		this.lblIdCourse = lblIdCourse;
	}

	public List<CourseDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataCourse();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
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
