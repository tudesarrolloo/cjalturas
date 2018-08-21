package com.cjalturas.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.*;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "inscription", schema = "${schema}")
public class Inscription implements java.io.Serializable {
  @Id
  @NotNull
  private Integer idInscription;

  @NotNull
  private Group group;

  @NotNull
  private Learner learner;

  @NotNull
  private Status status;

  private Date dateCertification;

  @NotNull
  private Date dateInscription;

  public Inscription() {
  }

  public Inscription(Integer idInscription, Date dateCertification, Date dateInscription, Group group, Learner learner, Status status) {
    this.idInscription = idInscription;
    this.group = group;
    this.learner = learner;
    this.status = status;
    this.dateCertification = dateCertification;
    this.dateInscription = dateInscription;
  }

  public Integer getIdInscription() {
    return this.idInscription;
  }

  public void setIdInscription(Integer idInscription) {
    this.idInscription = idInscription;
  }

  public Group getGroup() {
    return this.group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public Learner getLearner() {
    return this.learner;
  }

  public void setLearner(Learner learner) {
    this.learner = learner;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Date getDateCertification() {
    return this.dateCertification;
  }

  public void setDateCertification(Date dateCertification) {
    this.dateCertification = dateCertification;
  }

  public Date getDateInscription() {
    return this.dateInscription;
  }

  public void setDateInscription(Date dateInscription) {
    this.dateInscription = dateInscription;
  }
}
