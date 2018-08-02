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
@Table(name = "coach", schema = "${schema}")
public class Coach implements java.io.Serializable {
    @Id
    @NotNull
    private Integer idCoach;
    @NotNull
    private Person person;
    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String charge;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String licenseSst;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String sign;
    private Set<Group> groups = new HashSet<Group>(0);

    public Coach() {
    }

    public Coach(Integer idCoach, String charge, Set<Group> groups,
        String licenseSst, Person person, String sign) {
        this.idCoach = idCoach;
        this.person = person;
        this.charge = charge;
        this.licenseSst = licenseSst;
        this.sign = sign;
        this.groups = groups;
    }

    public Integer getIdCoach() {
        return this.idCoach;
    }

    public void setIdCoach(Integer idCoach) {
        this.idCoach = idCoach;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getLicenseSst() {
        return this.licenseSst;
    }

    public void setLicenseSst(String licenseSst) {
        this.licenseSst = licenseSst;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
