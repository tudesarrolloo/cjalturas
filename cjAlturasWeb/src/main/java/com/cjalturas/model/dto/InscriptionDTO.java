package com.cjalturas.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public class InscriptionDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(InscriptionDTO.class);

  private Date dateCertification;

  private Date dateInscription;

  private Integer idInscription;

  private Integer idGroup_Group;

  private Integer idLearner_Learner;

  private String code_Status;
  
  private String fullNameLearner;
  
  private String typeDocument;
  
  private String document;

  public Date getDateCertification() {
    return dateCertification;
  }

  public void setDateCertification(Date dateCertification) {
    this.dateCertification = dateCertification;
  }

  public Date getDateInscription() {
    return dateInscription;
  }

  public void setDateInscription(Date dateInscription) {
    this.dateInscription = dateInscription;
  }

  public Integer getIdInscription() {
    return idInscription;
  }

  public void setIdInscription(Integer idInscription) {
    this.idInscription = idInscription;
  }

  public Integer getIdGroup_Group() {
    return idGroup_Group;
  }

  public void setIdGroup_Group(Integer idGroup_Group) {
    this.idGroup_Group = idGroup_Group;
  }

  public Integer getIdLearner_Learner() {
    return idLearner_Learner;
  }

  public void setIdLearner_Learner(Integer idLearner_Learner) {
    this.idLearner_Learner = idLearner_Learner;
  }

  public String getCode_Status() {
    return code_Status;
  }

  public void setCode_Status(String code_Status) {
    this.code_Status = code_Status;
  }

  public String getFullNameLearner() {
    return fullNameLearner;
  }

  public void setFullNameLearner(String fullNameLearner) {
    this.fullNameLearner = fullNameLearner;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getTypeDocument() {
    return typeDocument;
  }

  public void setTypeDocument(String typeDocument) {
    this.typeDocument = typeDocument;
  }
  
}
