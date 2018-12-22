package com.cjalturas.model.dto;

import java.io.Serializable;

import com.cjalturas.model.Person;
import com.cjalturas.model.Rol;


/**
 * Dto para trasportar los datos relacionados con un usuario.
 * @author Edison
 */
public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer idUser;

  private String username;

  private String password;

  private boolean enabled;

  private Person person;

  private Rol rol;

  public Integer getIdUser() {
    return idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

}
