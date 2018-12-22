package com.cjalturas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "user", schema = "${schema}")
public class User implements java.io.Serializable {

  private static final long serialVersionUID = -526631833412423782L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idUser;

  @NotNull
  private Person person;

  @NotNull
  private Rol rol;

  @NotNull
  @NotEmpty
  @Size(max = 255)
  private String enabled;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String password;

  @NotNull
  @NotEmpty
  @Size(max = 60)
  private String username;

  public User() {
  }

  public User(Integer idUser, String enabled, String password, Person person, Rol rol, String username) {
    this.idUser = idUser;
    this.person = person;
    this.rol = rol;
    this.enabled = enabled;
    this.password = password;
    this.username = username;
  }

  public Integer getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Rol getRol() {
    return this.rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public String getEnabled() {
    return this.enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
