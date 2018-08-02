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
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "person", schema = "${schema}")
public class Person implements java.io.Serializable {
    @Id
    @NotNull
    private Integer idPerson;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String document;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String documentType;
    private String email;
    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String lastname;
    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String name;
    private String phone;
    private Set<Coach> coaches = new HashSet<Coach>(0);
    private Set<Learner> learners = new HashSet<Learner>(0);
    private Set<User> users = new HashSet<User>(0);

    public Person() {
    }

    public Person(Integer idPerson, Set<Coach> coaches, String document,
        String documentType, String email, String lastname,
        Set<Learner> learners, String name, String phone, Set<User> users) {
        this.idPerson = idPerson;
        this.document = document;
        this.documentType = documentType;
        this.email = email;
        this.lastname = lastname;
        this.name = name;
        this.phone = phone;
        this.coaches = coaches;
        this.learners = learners;
        this.users = users;
    }

    public Integer getIdPerson() {
        return this.idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Coach> getCoaches() {
        return this.coaches;
    }

    public void setCoaches(Set<Coach> coaches) {
        this.coaches = coaches;
    }

    public Set<Learner> getLearners() {
        return this.learners;
    }

    public void setLearners(Set<Learner> learners) {
        this.learners = learners;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
