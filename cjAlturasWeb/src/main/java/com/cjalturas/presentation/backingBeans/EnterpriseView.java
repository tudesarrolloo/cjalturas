package com.cjalturas.presentation.backingBeans;

import com.cjalturas.exceptions.*;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.EnterpriseDTO;

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
public class EnterpriseView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EnterpriseView.class);
    private InputText txtContactName;
    private InputText txtName;
    private InputText txtNit;
    private InputText txtPhone;
    private InputText txtIdEnterprise;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<EnterpriseDTO> data;
    private EnterpriseDTO selectedEnterprise;
    private Enterprise entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public EnterpriseView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedEnterprise = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedEnterprise = null;

        if (txtContactName != null) {
            txtContactName.setValue(null);
            txtContactName.setDisabled(true);
        }

        if (txtName != null) {
            txtName.setValue(null);
            txtName.setDisabled(true);
        }

        if (txtNit != null) {
            txtNit.setValue(null);
            txtNit.setDisabled(true);
        }

        if (txtPhone != null) {
            txtPhone.setValue(null);
            txtPhone.setDisabled(true);
        }

        if (txtIdEnterprise != null) {
            txtIdEnterprise.setValue(null);
            txtIdEnterprise.setDisabled(false);
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
            Integer idEnterprise = FacesUtils.checkInteger(txtIdEnterprise);
            entity = (idEnterprise != null)
                ? businessDelegatorView.getEnterprise(idEnterprise) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtContactName.setDisabled(false);
            txtName.setDisabled(false);
            txtNit.setDisabled(false);
            txtPhone.setDisabled(false);
            txtIdEnterprise.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtContactName.setValue(entity.getContactName());
            txtContactName.setDisabled(false);
            txtName.setValue(entity.getName());
            txtName.setDisabled(false);
            txtNit.setValue(entity.getNit());
            txtNit.setDisabled(false);
            txtPhone.setValue(entity.getPhone());
            txtPhone.setDisabled(false);
            txtIdEnterprise.setValue(entity.getIdEnterprise());
            txtIdEnterprise.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedEnterprise = (EnterpriseDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedEnterprise"));
        txtContactName.setValue(selectedEnterprise.getContactName());
        txtContactName.setDisabled(false);
        txtName.setValue(selectedEnterprise.getName());
        txtName.setDisabled(false);
        txtNit.setValue(selectedEnterprise.getNit());
        txtNit.setDisabled(false);
        txtPhone.setValue(selectedEnterprise.getPhone());
        txtPhone.setDisabled(false);
        txtIdEnterprise.setValue(selectedEnterprise.getIdEnterprise());
        txtIdEnterprise.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedEnterprise == null) && (entity == null)) {
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
            entity = new Enterprise();

            Integer idEnterprise = FacesUtils.checkInteger(txtIdEnterprise);

            entity.setContactName(FacesUtils.checkString(txtContactName));
            entity.setIdEnterprise(idEnterprise);
            entity.setName(FacesUtils.checkString(txtName));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setPhone(FacesUtils.checkString(txtPhone));
            businessDelegatorView.saveEnterprise(entity);
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
                Integer idEnterprise = new Integer(selectedEnterprise.getIdEnterprise());
                entity = businessDelegatorView.getEnterprise(idEnterprise);
            }

            entity.setContactName(FacesUtils.checkString(txtContactName));
            entity.setName(FacesUtils.checkString(txtName));
            entity.setNit(FacesUtils.checkString(txtNit));
            entity.setPhone(FacesUtils.checkString(txtPhone));
            businessDelegatorView.updateEnterprise(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedEnterprise = (EnterpriseDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedEnterprise"));

            Integer idEnterprise = new Integer(selectedEnterprise.getIdEnterprise());
            entity = businessDelegatorView.getEnterprise(idEnterprise);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idEnterprise = FacesUtils.checkInteger(txtIdEnterprise);
            entity = businessDelegatorView.getEnterprise(idEnterprise);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteEnterprise(entity);
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

    public String action_modifyWitDTO(String contactName, Integer idEnterprise,
        String name, String nit, String phone) throws Exception {
        try {
            entity.setContactName(FacesUtils.checkString(contactName));
            entity.setName(FacesUtils.checkString(name));
            entity.setNit(FacesUtils.checkString(nit));
            entity.setPhone(FacesUtils.checkString(phone));
            businessDelegatorView.updateEnterprise(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("EnterpriseView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtContactName() {
        return txtContactName;
    }

    public void setTxtContactName(InputText txtContactName) {
        this.txtContactName = txtContactName;
    }

    public InputText getTxtName() {
        return txtName;
    }

    public void setTxtName(InputText txtName) {
        this.txtName = txtName;
    }

    public InputText getTxtNit() {
        return txtNit;
    }

    public void setTxtNit(InputText txtNit) {
        this.txtNit = txtNit;
    }

    public InputText getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(InputText txtPhone) {
        this.txtPhone = txtPhone;
    }

    public InputText getTxtIdEnterprise() {
        return txtIdEnterprise;
    }

    public void setTxtIdEnterprise(InputText txtIdEnterprise) {
        this.txtIdEnterprise = txtIdEnterprise;
    }

    public List<EnterpriseDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataEnterprise();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<EnterpriseDTO> enterpriseDTO) {
        this.data = enterpriseDTO;
    }

    public EnterpriseDTO getSelectedEnterprise() {
        return selectedEnterprise;
    }

    public void setSelectedEnterprise(EnterpriseDTO enterprise) {
        this.selectedEnterprise = enterprise;
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
