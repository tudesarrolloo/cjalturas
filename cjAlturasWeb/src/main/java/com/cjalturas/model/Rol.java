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
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "rol", schema = "${schema}")
public class Rol implements java.io.Serializable {
  @Id
  @NotNull
  private String codeRol;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String rol;

  private Set<Permit> permits = new HashSet<Permit>(0);

  private Set<User> users = new HashSet<User>(0);

  public Rol() {
  }
  
  public Rol(String codeRol) {
    this.codeRol = codeRol;
  }

  public Rol(String codeRol, Set<Permit> permits, String rol, Set<User> users) {
    this.codeRol = codeRol;
    this.rol = rol;
    this.permits = permits;
    this.users = users;
  }

  public String getCodeRol() {
    return this.codeRol;
  }

  public void setCodeRol(String codeRol) {
    this.codeRol = codeRol;
  }

  public String getRol() {
    return this.rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  public Set<Permit> getPermits() {
    return this.permits;
  }

  public void setPermits(Set<Permit> permits) {
    this.permits = permits;
  }

  public Set<User> getUsers() {
    return this.users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
