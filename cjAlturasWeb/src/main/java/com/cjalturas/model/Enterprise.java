package com.cjalturas.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.*;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "enterprise", schema = "${schema}")
public class Enterprise implements java.io.Serializable {
  @Id
  @NotNull
  private Integer idEnterprise;

  private String contactName;

  @NotNull
  @NotEmpty
  @Size(max = 200)
  private String name;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String nit;

  private String phone;

  private Set<Learner> learners = new HashSet<Learner>(0);

  public Enterprise() {
  }

  public Enterprise(Integer idEnterprise, String contactName, Set<Learner> learners, String name, String nit, String phone) {
    this.idEnterprise = idEnterprise;
    this.contactName = contactName;
    this.name = name;
    this.nit = nit;
    this.phone = phone;
    this.learners = learners;
  }

  public Integer getIdEnterprise() {
    return this.idEnterprise;
  }

  public void setIdEnterprise(Integer idEnterprise) {
    this.idEnterprise = idEnterprise;
  }

  public String getContactName() {
    return this.contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNit() {
    return this.nit;
  }

  public void setNit(String nit) {
    this.nit = nit;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Set<Learner> getLearners() {
    return this.learners;
  }

  public void setLearners(Set<Learner> learners) {
    this.learners = learners;
  }
}
