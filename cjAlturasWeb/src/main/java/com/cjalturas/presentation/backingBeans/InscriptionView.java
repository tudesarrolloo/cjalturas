package com.cjalturas.presentation.backingBeans;

import com.cjalturas.exceptions.*;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.InscriptionDTO;

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
public class InscriptionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(InscriptionView.class);
    private InputText txtIdGroup_Group;
    private InputText txtIdLearner_Learner;
    private InputText txtCode_Status;
    private InputText txtIdInscription;
    private Calendar txtDateCertification;
    private Calendar txtDateInscription;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<InscriptionDTO> data;
    private InscriptionDTO selectedInscription;
    private Inscription entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public InscriptionView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedInscription = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedInscription = null;

        if (txtIdGroup_Group != null) {
            txtIdGroup_Group.setValue(null);
            txtIdGroup_Group.setDisabled(true);
        }

        if (txtIdLearner_Learner != null) {
            txtIdLearner_Learner.setValue(null);
            txtIdLearner_Learner.setDisabled(true);
        }

        if (txtCode_Status != null) {
            txtCode_Status.setValue(null);
            txtCode_Status.setDisabled(true);
        }

        if (txtDateCertification != null) {
            txtDateCertification.setValue(null);
            txtDateCertification.setDisabled(true);
        }

        if (txtDateInscription != null) {
            txtDateInscription.setValue(null);
            txtDateInscription.setDisabled(true);
        }

        if (txtIdInscription != null) {
            txtIdInscription.setValue(null);
            txtIdInscription.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtDateCertification() {
        Date inputDate = (Date) txtDateCertification.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtDateInscription() {
        Date inputDate = (Date) txtDateInscription.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Integer idInscription = FacesUtils.checkInteger(txtIdInscription);
            entity = (idInscription != null)
                ? businessDelegatorView.getInscription(idInscription) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdGroup_Group.setDisabled(false);
            txtIdLearner_Learner.setDisabled(false);
            txtCode_Status.setDisabled(false);
            txtDateCertification.setDisabled(false);
            txtDateInscription.setDisabled(false);
            txtIdInscription.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDateCertification.setValue(entity.getDateCertification());
            txtDateCertification.setDisabled(false);
            txtDateInscription.setValue(entity.getDateInscription());
            txtDateInscription.setDisabled(false);
            txtIdGroup_Group.setValue(entity.getGroup().getIdGroup());
            txtIdGroup_Group.setDisabled(false);
            txtIdLearner_Learner.setValue(entity.getLearner().getIdLearner());
            txtIdLearner_Learner.setDisabled(false);
            txtCode_Status.setValue(entity.getStatus().getCode());
            txtCode_Status.setDisabled(false);
            txtIdInscription.setValue(entity.getIdInscription());
            txtIdInscription.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedInscription = (InscriptionDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedInscription"));
        txtDateCertification.setValue(selectedInscription.getDateCertification());
        txtDateCertification.setDisabled(false);
        txtDateInscription.setValue(selectedInscription.getDateInscription());
        txtDateInscription.setDisabled(false);
        txtIdGroup_Group.setValue(selectedInscription.getIdGroup_Group());
        txtIdGroup_Group.setDisabled(false);
        txtIdLearner_Learner.setValue(selectedInscription.getIdLearner_Learner());
        txtIdLearner_Learner.setDisabled(false);
        txtCode_Status.setValue(selectedInscription.getCode_Status());
        txtCode_Status.setDisabled(false);
        txtIdInscription.setValue(selectedInscription.getIdInscription());
        txtIdInscription.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedInscription == null) && (entity == null)) {
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
            entity = new Inscription();

            Integer idInscription = FacesUtils.checkInteger(txtIdInscription);

            entity.setDateCertification(FacesUtils.checkDate(
                    txtDateCertification));
            entity.setDateInscription(FacesUtils.checkDate(txtDateInscription));
            entity.setIdInscription(idInscription);
            entity.setGroup((FacesUtils.checkInteger(txtIdGroup_Group) != null)
                ? businessDelegatorView.getGroup(FacesUtils.checkInteger(
                        txtIdGroup_Group)) : null);
            entity.setLearner((FacesUtils.checkInteger(txtIdLearner_Learner) != null)
                ? businessDelegatorView.getLearner(FacesUtils.checkInteger(
                        txtIdLearner_Learner)) : null);
            entity.setStatus((FacesUtils.checkString(txtCode_Status) != null)
                ? businessDelegatorView.getStatus(FacesUtils.checkString(
                        txtCode_Status)) : null);
            businessDelegatorView.saveInscription(entity);
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
                Integer idInscription = new Integer(selectedInscription.getIdInscription());
                entity = businessDelegatorView.getInscription(idInscription);
            }

            entity.setDateCertification(FacesUtils.checkDate(
                    txtDateCertification));
            entity.setDateInscription(FacesUtils.checkDate(txtDateInscription));
            entity.setGroup((FacesUtils.checkInteger(txtIdGroup_Group) != null)
                ? businessDelegatorView.getGroup(FacesUtils.checkInteger(
                        txtIdGroup_Group)) : null);
            entity.setLearner((FacesUtils.checkInteger(txtIdLearner_Learner) != null)
                ? businessDelegatorView.getLearner(FacesUtils.checkInteger(
                        txtIdLearner_Learner)) : null);
            entity.setStatus((FacesUtils.checkString(txtCode_Status) != null)
                ? businessDelegatorView.getStatus(FacesUtils.checkString(
                        txtCode_Status)) : null);
            businessDelegatorView.updateInscription(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedInscription = (InscriptionDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedInscription"));

            Integer idInscription = new Integer(selectedInscription.getIdInscription());
            entity = businessDelegatorView.getInscription(idInscription);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Integer idInscription = FacesUtils.checkInteger(txtIdInscription);
            entity = businessDelegatorView.getInscription(idInscription);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteInscription(entity);
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

    public String action_modifyWitDTO(Date dateCertification,
        Date dateInscription, Integer idInscription, Integer idGroup_Group,
        Integer idLearner_Learner, String code_Status)
        throws Exception {
        try {
            entity.setDateCertification(FacesUtils.checkDate(dateCertification));
            entity.setDateInscription(FacesUtils.checkDate(dateInscription));
            businessDelegatorView.updateInscription(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("InscriptionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdGroup_Group() {
        return txtIdGroup_Group;
    }

    public void setTxtIdGroup_Group(InputText txtIdGroup_Group) {
        this.txtIdGroup_Group = txtIdGroup_Group;
    }

    public InputText getTxtIdLearner_Learner() {
        return txtIdLearner_Learner;
    }

    public void setTxtIdLearner_Learner(InputText txtIdLearner_Learner) {
        this.txtIdLearner_Learner = txtIdLearner_Learner;
    }

    public InputText getTxtCode_Status() {
        return txtCode_Status;
    }

    public void setTxtCode_Status(InputText txtCode_Status) {
        this.txtCode_Status = txtCode_Status;
    }

    public Calendar getTxtDateCertification() {
        return txtDateCertification;
    }

    public void setTxtDateCertification(Calendar txtDateCertification) {
        this.txtDateCertification = txtDateCertification;
    }

    public Calendar getTxtDateInscription() {
        return txtDateInscription;
    }

    public void setTxtDateInscription(Calendar txtDateInscription) {
        this.txtDateInscription = txtDateInscription;
    }

    public InputText getTxtIdInscription() {
        return txtIdInscription;
    }

    public void setTxtIdInscription(InputText txtIdInscription) {
        this.txtIdInscription = txtIdInscription;
    }

    public List<InscriptionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataInscription();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<InscriptionDTO> inscriptionDTO) {
        this.data = inscriptionDTO;
    }

    public InscriptionDTO getSelectedInscription() {
        return selectedInscription;
    }

    public void setSelectedInscription(InscriptionDTO inscription) {
        this.selectedInscription = inscription;
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
