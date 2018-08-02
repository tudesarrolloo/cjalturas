package com.cjalturas.model;

import org.hibernate.validator.constraints.*;

import java.util.Date;

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
@Table(name = "permit", schema = "${schema}")
public class Permit implements java.io.Serializable {
    @Id
    @NotNull
    private Integer idPermit;
    @NotNull
    private Rol rol;
    @NotNull
    @NotEmpty
    @Size(max = 60)
    private String resource;

    public Permit() {
    }

    public Permit(Integer idPermit, String resource, Rol rol) {
        this.idPermit = idPermit;
        this.rol = rol;
        this.resource = resource;
    }

    public Integer getIdPermit() {
        return this.idPermit;
    }

    public void setIdPermit(Integer idPermit) {
        this.idPermit = idPermit;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
