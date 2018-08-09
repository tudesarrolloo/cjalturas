package com.cjalturas.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "course", schema = "${schema}")
public class Course implements java.io.Serializable {
    @Id
//    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCourse;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String course;
    private Set<Group> groups = new HashSet<Group>(0);

    public Course() {
    }

    public Course(Integer idCourse, String course, Set<Group> groups) {
        this.idCourse = idCourse;
        this.course = course;
        this.groups = groups;
    }

    public Integer getIdCourse() {
        return this.idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
