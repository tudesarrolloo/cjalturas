package com.cjalturas.presentation.backingBeans;

import com.cjalturas.exceptions.*;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.PermitDTO;

import com.cjalturas.presentation.businessDelegate.*;

import com.cjalturas.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

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
public class PermitView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PermitView.class);
    private InputText txtResource;
    private InputText txtCodeRol_Rol;
    private InputText txtIdPermit;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<PermitDTO> data;
    private PermitDTO selectedPermit;
    private Permit entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public PermitView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedPermit = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedPermit = null;

        if (txtResource != null) {
            txtResource.setValue(null);
            txtResource.setDisabled(true);
        }

        if (txtCodeRol_Rol != null) {
            txtCodeRol_Rol.setValue(null);
            txtCodeRol_Rol.setDisabled(true);
        }

        if (txtIdPermit != null) {
            txtIdPermit.setValue(null);
            txtIdPermit.setDisabled(false);
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
            Integer idPermit = FacesUtils.checkInteger(txtIdPermit);
            entity = (idPermit != null)
                ? businessDelegatorView.getPermit(idPermit) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtResource.setDisabled(false);
            txtCodeRol_Rol.setDisabled(false);
            txtIdPermit.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtResource.setValue(entity.getResource());
            txtResource.setDisabled(false);
            txtCodeRol_Rol.setValue(entity.getRol().getCodeRol());
            txtCodeRol_Rol.setDisabled(false);
            txtIdPermit.setValue(entity.getIdPermit());
            txtIdPermit.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedPermit = (PermitDTO) (evt.getComponent().getAttributes()
                                         .get("selectedPermit"));
        txtResource.setValue(selectedPermit.getResource());
        txtResource.setDisabled(false);
        txtCodeRol_Rol.setValue(selectedPermit.getCodeRol_Rol());
        txtCodeRol_Rol.setDisabled(false);
        txtIdPermit.setValue(selectedPermit.getIdPermit());
        txtIdPermit.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedPermit == null) && (entity == null)) {
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
            entity = new Permit();

            Integer idPermit = FacesUtils.checkInteger(txtIdPermit);

            entity.setIdPermit(idPermit);
            entity.setResource(FacesUtils.checkString(txtResource));
            entity.setRol((FacesUtils.checkString(txtCodeRol_Rol) != null)
                ? businessDelegatorView.getRol(FacesUtils.checkString(
                        txtCodeRol_Rol)) : null);
            businessDelegatorView.savePermit(entity);
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
                Integer idPermit = new Integer(selectedPermit.getIdPermit());
                entity = businessDelegatorView.getPermit(idPermit);
            }

            entity.setResource(FacesUtils.checkString(txtResource));
            entity.setRol((FacesUtils.checkString(txtCodeRol_Rol) != null)
                ? businessDelegatorView.getRol(FacesUtils.checkString(
                        txtCodeRol_Rol)) : null);
            businessDelegatorView.updatePermit(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedPermit = (PermitDTO) (evt.getComponent().getAttributes()
                                             .get("selectedPermit"));

            Integer idPermit = new Integer(selectedPermit.getIdPermit());
            entity = businessDelegatorView.getPermit(idPermit);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idPermit = FacesUtils.checkInteger(txtIdPermit);
            entity = businessDelegatorView.getPermit(idPermit);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deletePermit(entity);
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

    public String action_modifyWitDTO(Integer idPermit, String resource,
        String codeRol_Rol) throws Exception {
        try {
            entity.setResource(FacesUtils.checkString(resource));
            businessDelegatorView.updatePermit(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("PermitView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtResource() {
        return txtResource;
    }

    public void setTxtResource(InputText txtResource) {
        this.txtResource = txtResource;
    }

    public InputText getTxtCodeRol_Rol() {
        return txtCodeRol_Rol;
    }

    public void setTxtCodeRol_Rol(InputText txtCodeRol_Rol) {
        this.txtCodeRol_Rol = txtCodeRol_Rol;
    }

    public InputText getTxtIdPermit() {
        return txtIdPermit;
    }

    public void setTxtIdPermit(InputText txtIdPermit) {
        this.txtIdPermit = txtIdPermit;
    }

    public List<PermitDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataPermit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<PermitDTO> permitDTO) {
        this.data = permitDTO;
    }

    public PermitDTO getSelectedPermit() {
        return selectedPermit;
    }

    public void setSelectedPermit(PermitDTO permit) {
        this.selectedPermit = permit;
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
