package com.cjalturas.model.control;

import com.cjalturas.model.Permit;
import com.cjalturas.model.dto.PermitDTO;

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
public interface IPermitLogic {
    public List<Permit> getPermit() throws Exception;

    /**
         * Save an new Permit entity
         */
    public void savePermit(Permit entity) throws Exception;

    /**
         * Delete an existing Permit entity
         *
         */
    public void deletePermit(Permit entity) throws Exception;

    /**
        * Update an existing Permit entity
        *
        */
    public void updatePermit(Permit entity) throws Exception;

    /**
         * Load an existing Permit entity
         *
         */
    public Permit getPermit(Integer idPermit) throws Exception;

    public List<Permit> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Permit> findPagePermit(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPermit() throws Exception;

    public List<PermitDTO> getDataPermit() throws Exception;

    public void validatePermit(Permit permit) throws Exception;
}
