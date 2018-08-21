package com.cjalturas.model.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public class CoachDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(CoachDTO.class);

  private String charge;

  private Integer idCoach;

  private String licenseSst;

  private String sign;

  private Integer idPerson_Person;

  public String getCharge() {
    return charge;
  }

  public void setCharge(String charge) {
    this.charge = charge;
  }

  public Integer getIdCoach() {
    return idCoach;
  }

  public void setIdCoach(Integer idCoach) {
    this.idCoach = idCoach;
  }

  public String getLicenseSst() {
    return licenseSst;
  }

  public void setLicenseSst(String licenseSst) {
    this.licenseSst = licenseSst;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public Integer getIdPerson_Person() {
    return idPerson_Person;
  }

  public void setIdPerson_Person(Integer idPerson_Person) {
    this.idPerson_Person = idPerson_Person;
  }
}
