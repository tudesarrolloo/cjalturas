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
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UserDTO.class);
    private String enabled;
    private Integer idUser;
    private String password;
    private String username;
    private Integer idPerson_Person;
    private String codeRol_Rol;

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getIdPerson_Person() {
        return idPerson_Person;
    }

    public void setIdPerson_Person(Integer idPerson_Person) {
        this.idPerson_Person = idPerson_Person;
    }

    public String getCodeRol_Rol() {
        return codeRol_Rol;
    }

    public void setCodeRol_Rol(String codeRol_Rol) {
        this.codeRol_Rol = codeRol_Rol;
    }
}
