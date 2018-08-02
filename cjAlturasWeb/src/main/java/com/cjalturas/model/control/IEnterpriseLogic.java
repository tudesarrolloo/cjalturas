package com.cjalturas.model.control;

import com.cjalturas.model.Enterprise;
import com.cjalturas.model.dto.EnterpriseDTO;

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
public interface IEnterpriseLogic {
    public List<Enterprise> getEnterprise() throws Exception;

    /**
         * Save an new Enterprise entity
         */
    public void saveEnterprise(Enterprise entity) throws Exception;

    /**
         * Delete an existing Enterprise entity
         *
         */
    public void deleteEnterprise(Enterprise entity) throws Exception;

    /**
        * Update an existing Enterprise entity
        *
        */
    public void updateEnterprise(Enterprise entity) throws Exception;

    /**
         * Load an existing Enterprise entity
         *
         */
    public Enterprise getEnterprise(Integer idEnterprise)
        throws Exception;

    public List<Enterprise> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Enterprise> findPageEnterprise(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEnterprise() throws Exception;

    public List<EnterpriseDTO> getDataEnterprise() throws Exception;

    public void validateEnterprise(Enterprise enterprise)
        throws Exception;
}
