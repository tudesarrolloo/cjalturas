package com.cjalturas.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Entity
@Table(name = "learner", schema = "${schema}")
public class Learner implements java.io.Serializable {

  public static Map<String, String> map_fields;

  static {
    map_fields = new HashMap<>();
    map_fields.put("document", "Documento");
    map_fields.put("documentType", "Tipo de documento");
    map_fields.put("name", "Nombre");
    map_fields.put("lastname", "Apellidos");
    map_fields.put("phone", "Teléfono");
    map_fields.put("email", "E-mail");
    map_fields.put("economicsector", "Sector Económico");
    map_fields.put("enterprise", "Empresa");
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idLearner;

  @NotNull
  private Economicsector economicsector;

  @NotNull
  private Enterprise enterprise;

  @NotNull
  private Person person;

  private Set<Inscription> inscriptions = new HashSet<Inscription>(0);

  public Learner() {
  }

  public Learner(Integer idLearner, Economicsector economicsector, Enterprise enterprise, Set<Inscription> inscriptions, Person person) {
    this.idLearner = idLearner;
    this.economicsector = economicsector;
    this.enterprise = enterprise;
    this.person = person;
    this.inscriptions = inscriptions;
  }

  public Integer getIdLearner() {
    return this.idLearner;
  }

  public void setIdLearner(Integer idLearner) {
    this.idLearner = idLearner;
  }

  public Economicsector getEconomicsector() {
    return this.economicsector;
  }

  public void setEconomicsector(Economicsector economicsector) {
    this.economicsector = economicsector;
  }

  public Enterprise getEnterprise() {
    return this.enterprise;
  }

  public void setEnterprise(Enterprise enterprise) {
    this.enterprise = enterprise;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public Set<Inscription> getInscriptions() {
    return this.inscriptions;
  }

  public void setInscriptions(Set<Inscription> inscriptions) {
    this.inscriptions = inscriptions;
  }
}
