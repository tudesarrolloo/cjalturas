package com.cjalturas.model.dto;

import java.io.Serializable;


/**
 * Dto con la información básica de un curso.
 * @author Edison
 */
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String course;
    private Integer idCourse;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }
}
