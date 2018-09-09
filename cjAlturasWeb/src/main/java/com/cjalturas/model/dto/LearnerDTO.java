package com.cjalturas.model.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.model.Economicsector;
import com.cjalturas.model.Enterprise;
import com.cjalturas.model.Person;


/**
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public class LearnerDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(LearnerDTO.class);

  private Integer idLearner;

  private Integer idEconomicSector_Economicsector;

  private Integer idEnterprise_Enterprise;

  private Integer idPerson_Person;
  
  private Person person;
  
  private Enterprise enterprise;
  
  private Economicsector economicSector;

  public Integer getIdLearner() {
    return idLearner;
  }

  public void setIdLearner(Integer idLearner) {
    this.idLearner = idLearner;
  }

  public Integer getIdEconomicSector_Economicsector() {
    return idEconomicSector_Economicsector;
  }

  public void setIdEconomicSector_Economicsector(Integer idEconomicSector_Economicsector) {
    this.idEconomicSector_Economicsector = idEconomicSector_Economicsector;
  }

  public Integer getIdEnterprise_Enterprise() {
    return idEnterprise_Enterprise;
  }

  public void setIdEnterprise_Enterprise(Integer idEnterprise_Enterprise) {
    this.idEnterprise_Enterprise = idEnterprise_Enterprise;
  }

  public Integer getIdPerson_Person() {
    return idPerson_Person;
  }

  public void setIdPerson_Person(Integer idPerson_Person) {
    this.idPerson_Person = idPerson_Person;
  }
  
  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Enterprise getEnterprise() {
    return enterprise;
  }

  public void setEnterprise(Enterprise enterprise) {
    this.enterprise = enterprise;
  }

  public Economicsector getEconomicSector() {
    return economicSector;
  }

  public void setEconomicSector(Economicsector economicSector) {
    this.economicSector = economicSector;
  }
  
}
