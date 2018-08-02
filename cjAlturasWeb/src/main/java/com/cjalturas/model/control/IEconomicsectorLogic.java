package com.cjalturas.model.control;

import com.cjalturas.model.Economicsector;
import com.cjalturas.model.dto.EconomicsectorDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IEconomicsectorLogic {
    public List<Economicsector> getEconomicsector() throws Exception;

    /**
         * Save an new Economicsector entity
         */
    public void saveEconomicsector(Economicsector entity)
        throws Exception;

    /**
         * Delete an existing Economicsector entity
         *
         */
    public void deleteEconomicsector(Economicsector entity)
        throws Exception;

    /**
        * Update an existing Economicsector entity
        *
        */
    public void updateEconomicsector(Economicsector entity)
        throws Exception;

    /**
         * Load an existing Economicsector entity
         *
         */
    public Economicsector getEconomicsector(Integer idEconomicSector)
        throws Exception;

    public List<Economicsector> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Economicsector> findPageEconomicsector(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEconomicsector() throws Exception;

    public List<EconomicsectorDTO> getDataEconomicsector()
        throws Exception;

    public void validateEconomicsector(Economicsector economicsector)
        throws Exception;
}
