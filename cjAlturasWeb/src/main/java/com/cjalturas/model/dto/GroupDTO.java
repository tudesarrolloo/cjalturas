package com.cjalturas.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.model.Coach;
import com.cjalturas.model.Course;
import com.cjalturas.utilities.FormatUtils;


/**
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public class GroupDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(GroupDTO.class);

  private Integer idGroup;
  
  private String description;

  private Integer idCoach_Coach;

  private Integer idCourse_Course;
  
  private Date dateStart;
  
  private Date dateEnd;
  
  private String observations;
  
  private Integer status;
  
  private Coach coach;
  
  private Course course;
  
  private Integer learnersInGroup;

  public Date getDateStart() {
    return dateStart;
  }
  
  public String getDateStartFormated() {
    return FormatUtils.convertDate(dateStart);
  }

  public void setDateStart(Date dateStart) {
    this.dateStart = dateStart;
  }

  public Integer getIdGroup() {
    return idGroup;
  }

  public void setIdGroup(Integer idGroup) {
    this.idGroup = idGroup;
  }

  public String getObservations() {
    return observations;
  }

  public void setObservations(String observations) {
    this.observations = observations;
  }

  public Integer getIdCoach_Coach() {
    return idCoach_Coach;
  }

  public void setIdCoach_Coach(Integer idCoach_Coach) {
    this.idCoach_Coach = idCoach_Coach;
  }

  public Integer getIdCourse_Course() {
    return idCourse_Course;
  }

  public void setIdCourse_Course(Integer idCourse_Course) {
    this.idCourse_Course = idCourse_Course;
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
  
  public String getDateEndFormated() {
    return FormatUtils.convertDate(dateEnd);
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

  public Coach getCoach() {
    return coach;
  }

  public void setCoach(Coach coach) {
    this.coach = coach;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public Integer getLearnersInGroup() {
    return learnersInGroup;
  }

  public void setLearnersInGroup(Integer learnersInGroup) {
    this.learnersInGroup = learnersInGroup;
  }
  
}
