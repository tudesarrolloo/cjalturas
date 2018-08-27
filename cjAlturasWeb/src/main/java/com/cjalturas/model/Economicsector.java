package com.cjalturas.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Entidad que mapea con la tabla de sectores ecnonómicos en la base de datos.
 * @author Edison
 */
@Entity
@Table(name = "economicsector", schema = "${schema}")
public class Economicsector implements java.io.Serializable {
  
  private static final long serialVersionUID = 2536944851730023886L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idEconomicSector;

  @NotNull
  @NotEmpty
  @Size(max = 20)
  private String economicSector;

  private Set<Learner> learners = new HashSet<Learner>(0);

  public Economicsector() {
  }

  public Economicsector(Integer idEconomicSector, String economicSector, Set<Learner> learners) {
    this.idEconomicSector = idEconomicSector;
    this.economicSector = economicSector;
    this.learners = learners;
  }

  public Integer getIdEconomicSector() {
    return this.idEconomicSector;
  }

  public void setIdEconomicSector(Integer idEconomicSector) {
    this.idEconomicSector = idEconomicSector;
  }

  public String getEconomicSector() {
    return this.economicSector;
  }

  public void setEconomicSector(String economicSector) {
    this.economicSector = economicSector;
  }

  public Set<Learner> getLearners() {
    return this.learners;
  }

  public void setLearners(Set<Learner> learners) {
    this.learners = learners;
  }
}
