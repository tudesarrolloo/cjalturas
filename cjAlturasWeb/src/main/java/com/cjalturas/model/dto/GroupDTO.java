package com.cjalturas.model.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class GroupDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(GroupDTO.class);
    private Date dateStart;
    private Integer idGroup;
    private String observations;
    private Integer idCoach_Coach;
    private Integer idCourse_Course;

    public Date getDateStart() {
        return dateStart;
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
}
