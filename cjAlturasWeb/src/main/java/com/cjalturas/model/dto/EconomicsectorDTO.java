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
public class EconomicsectorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(EconomicsectorDTO.class);
    private String economicSector;
    private Integer idEconomicSector;

    public String getEconomicSector() {
        return economicSector;
    }

    public void setEconomicSector(String economicSector) {
        this.economicSector = economicSector;
    }

    public Integer getIdEconomicSector() {
        return idEconomicSector;
    }

    public void setIdEconomicSector(Integer idEconomicSector) {
        this.idEconomicSector = idEconomicSector;
    }
}
