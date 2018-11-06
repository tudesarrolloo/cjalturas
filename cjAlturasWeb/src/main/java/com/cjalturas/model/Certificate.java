package com.cjalturas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "certificate", schema = "${schema}")
public class Certificate implements java.io.Serializable {

  private static final long serialVersionUID = -1226655337503884684L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idCertificate;

  @NotNull
  private Inscription inscription;

  @NotNull
  @NotEmpty
  @Size(max = 30)
  private String date;

  @Size(max = 20)
  private String code;

  @NotNull
  @NotEmpty
  @Size(max = 300)
  private String certification;

  @NotNull
  @NotEmpty
  @Size(max = 100)
  private String learner;

  @NotNull
  @NotEmpty
  @Size(max = 30)
  private String learnerDocument;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String level;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String intensity;

  @NotNull
  @NotEmpty
  @Size(max = 100)
  private String days;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String city;

  @NotNull
  @NotEmpty
  @Size(max = 100)
  private String instructor1;

  @NotNull
  @NotEmpty
  @Size(max = 30)
  private String instructor1Charge;

  @NotNull
  @NotEmpty
  private String instructor1Sign;

  @NotNull
  @NotEmpty
  @Size(max = 100)
  private String instructor2;

  @NotNull
  @NotEmpty
  @Size(max = 30)
  private String instructor2Charge;

  @NotNull
  @NotEmpty
  private String instructor2Sign;
  
  @NotNull
  private Date dateExpiration;

  public Certificate() {
  }

  public Certificate(Integer idCertificate, Inscription inscription, String date, String code, String certification, String learner, String learnerDocument,
      String level, String intensity, String days, String city, String instructor1, String instructor1Charge, String instructor1Sign, String instructor2,
      String instructor2Charge, String instructor2Sign) {
    super();
    this.idCertificate = idCertificate;
    this.inscription = inscription;
    this.date = date;
    this.code = code;
    this.certification = certification;
    this.learner = learner;
    this.learnerDocument = learnerDocument;
    this.level = level;
    this.intensity = intensity;
    this.days = days;
    this.city = city;
    this.instructor1 = instructor1;
    this.instructor1Charge = instructor1Charge;
    this.instructor1Sign = instructor1Sign;
    this.instructor2 = instructor2;
    this.instructor2Charge = instructor2Charge;
    this.instructor2Sign = instructor2Sign;
  }



  public Integer getIdCertificate() {
    return idCertificate;
  }

  public void setIdCertificate(Integer idCertificate) {
    this.idCertificate = idCertificate;
  }

  public Inscription getInscription() {
    return inscription;
  }

  public void setInscription(Inscription inscription) {
    this.inscription = inscription;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCertification() {
    return certification;
  }

  public void setCertification(String certification) {
    this.certification = certification;
  }

  public String getLearner() {
    return learner;
  }

  public void setLearner(String learner) {
    this.learner = learner;
  }

  public String getLearnerDocument() {
    return learnerDocument;
  }

  public void setLearnerDocument(String learnerDocument) {
    this.learnerDocument = learnerDocument;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getIntensity() {
    return intensity;
  }

  public void setIntensity(String intensity) {
    this.intensity = intensity;
  }

  public String getDays() {
    return days;
  }

  public void setDays(String days) {
    this.days = days;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getInstructor1() {
    return instructor1;
  }

  public void setInstructor1(String instructor1) {
    this.instructor1 = instructor1;
  }

  public String getInstructor1Charge() {
    return instructor1Charge;
  }

  public void setInstructor1Charge(String instructor1Charge) {
    this.instructor1Charge = instructor1Charge;
  }

  public String getInstructor1Sign() {
    return instructor1Sign;
  }

  public void setInstructor1Sign(String instructor1Sign) {
    this.instructor1Sign = instructor1Sign;
  }

  public String getInstructor2() {
    return instructor2;
  }

  public void setInstructor2(String instructor2) {
    this.instructor2 = instructor2;
  }

  public String getInstructor2Charge() {
    return instructor2Charge;
  }

  public void setInstructor2Charge(String instructor2Charge) {
    this.instructor2Charge = instructor2Charge;
  }

  public String getInstructor2Sign() {
    return instructor2Sign;
  }

  public void setInstructor2Sign(String instructor2Sign) {
    this.instructor2Sign = instructor2Sign;
  }

  public Date getDateExpiration() {
    return dateExpiration;
  }

  public void setDateExpiration(Date dateExpiration) {
    this.dateExpiration = dateExpiration;
  }
  
}
