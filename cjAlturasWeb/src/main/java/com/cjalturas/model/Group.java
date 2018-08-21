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
@Table(name = "group", schema = "${schema}")
public class Group implements java.io.Serializable {
  @Id
  @NotNull
  private Integer idGroup;

  @NotNull
  private Coach coach;

  @NotNull
  private Course course;

  @NotNull
  private Date dateStart;

  private String observations;

  private Set<Inscription> inscriptions = new HashSet<Inscription>(0);

  public Group() {
  }

  public Group(Integer idGroup, Coach coach, Course course, Date dateStart, Set<Inscription> inscriptions, String observations) {
    this.idGroup = idGroup;
    this.coach = coach;
    this.course = course;
    this.dateStart = dateStart;
    this.observations = observations;
    this.inscriptions = inscriptions;
  }

  public Integer getIdGroup() {
    return this.idGroup;
  }

  public void setIdGroup(Integer idGroup) {
    this.idGroup = idGroup;
  }

  public Coach getCoach() {
    return this.coach;
  }

  public void setCoach(Coach coach) {
    this.coach = coach;
  }

  public Course getCourse() {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Date getDateStart() {
    return this.dateStart;
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  public String getObservations() {
    return this.observations;
  }

  public void setObservations(String observations) {
    this.observations = observations;
  }

  public Set<Inscription> getInscriptions() {
    return this.inscriptions;
  }

  public void setInscriptions(Set<Inscription> inscriptions) {
    this.inscriptions = inscriptions;
  }
}
