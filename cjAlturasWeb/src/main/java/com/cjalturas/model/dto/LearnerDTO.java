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
public class LearnerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LearnerDTO.class);
    private Integer idLearner;
    private Integer idEconomicSector_Economicsector;
    private Integer idEnterprise_Enterprise;
    private Integer idPerson_Person;

    public Integer getIdLearner() {
        return idLearner;
    }

    public void setIdLearner(Integer idLearner) {
        this.idLearner = idLearner;
    }

    public Integer getIdEconomicSector_Economicsector() {
        return idEconomicSector_Economicsector;
    }

    public void setIdEconomicSector_Economicsector(
        Integer idEconomicSector_Economicsector) {
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
}
