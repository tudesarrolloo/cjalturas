package com.cjalturas.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
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
@Table(name = "status", schema = "${schema}")
public class Status implements java.io.Serializable {

  private static final long serialVersionUID = -8479193777777997287L;

  /** Estado que indica que una inscripción se encuentra activa */
  public static final String ACTIVE_CODE = "ACT";
  
  /** Estado que indica que una inscripción ya se ceritificó */
  public static final String CERTIFICATE_CODE = "CER";

  /** Estado que indica que una inscripción se encuentra activa */
  public static final String ACTIVE_DESCRIPTION = "Activo";
  
  /** Estado que indica que una inscripción ya se ceritificó */
  public static final String CERTIFICATE_DESCRIPTION = "Certificado";

  public static final Status ACTIVE = new Status(ACTIVE_CODE, ACTIVE_DESCRIPTION);
  public static final Status CERTIFICATE = new Status(CERTIFICATE_CODE, CERTIFICATE_DESCRIPTION);

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

  public Status(String code, String status) {
    super();
    this.code = code;
    this.status = status;
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
