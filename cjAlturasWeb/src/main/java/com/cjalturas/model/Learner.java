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
@Table(name = "learner", schema = "${schema}")
public class Learner implements java.io.Serializable {
    @Id
    @NotNull
    private Integer idLearner;
    @NotNull
    private Economicsector economicsector;
    @NotNull
    private Enterprise enterprise;
    @NotNull
    private Person person;
    private Set<Inscription> inscriptions = new HashSet<Inscription>(0);

    public Learner() {
    }

    public Learner(Integer idLearner, Economicsector economicsector,
        Enterprise enterprise, Set<Inscription> inscriptions, Person person) {
        this.idLearner = idLearner;
        this.economicsector = economicsector;
        this.enterprise = enterprise;
        this.person = person;
        this.inscriptions = inscriptions;
    }

    public Integer getIdLearner() {
        return this.idLearner;
    }

    public void setIdLearner(Integer idLearner) {
        this.idLearner = idLearner;
    }

    public Economicsector getEconomicsector() {
        return this.economicsector;
    }

    public void setEconomicsector(Economicsector economicsector) {
        this.economicsector = economicsector;
    }

    public Enterprise getEnterprise() {
        return this.enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Inscription> getInscriptions() {
        return this.inscriptions;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
