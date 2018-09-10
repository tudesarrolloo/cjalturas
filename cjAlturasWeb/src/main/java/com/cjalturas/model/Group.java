package com.cjalturas.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


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

  private String description;

  @NotNull
  private Course course;

  @NotNull
  private Date dateStart;

  private Date dateEnd;

  private String observations;

  private Integer status;

  private Set<Inscription> inscriptions = new HashSet<Inscription>(0);

  public Group() {
  }

  public Group(Integer idGroup, Coach coach, String description, Course course, Date dateStart, Date dateEnd, String observations, Integer status) {
    super();
    this.idGroup = idGroup;
    this.coach = coach;
    this.description = description;
    this.course = course;
    this.dateStart = dateStart;
    this.dateEnd = dateEnd;
    this.observations = observations;
    this.status = status;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(Date dateEnd) {
    this.dateEnd = dateEnd;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
