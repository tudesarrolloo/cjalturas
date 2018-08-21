package com.cjalturas.model.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public class PermitDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(PermitDTO.class);

  private Integer idPermit;

  private String resource;

  private String codeRol_Rol;

  public Integer getIdPermit() {
    return idPermit;
  }

  public void setIdPermit(Integer idPermit) {
    this.idPermit = idPermit;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getCodeRol_Rol() {
    return codeRol_Rol;
  }

  public void setCodeRol_Rol(String codeRol_Rol) {
    this.codeRol_Rol = codeRol_Rol;
  }
}
