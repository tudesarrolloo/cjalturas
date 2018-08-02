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
@Table(name = "status", schema = "${schema}")
public class Status implements java.io.Serializable {
    @Id
    @NotNull
    private String code;
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String status;
    private Set<Inscription> inscriptions = new HashSet<Inscription>(0);

    public Status() {
    }

    public Status(String code, Set<Inscription> inscriptions, String status) {
        this.code = code;
        this.status = status;
        this.inscriptions = inscriptions;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Inscription> getInscriptions() {
        return this.inscriptions;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
