package com.cjalturas.model.control;

import com.cjalturas.model.Coach;
import com.cjalturas.model.dto.CoachDTO;

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
public interface ICoachLogic {
    public List<Coach> getCoach() throws Exception;

    /**
         * Save an new Coach entity
         */
    public void saveCoach(Coach entity) throws Exception;

    /**
         * Delete an existing Coach entity
         *
         */
    public void deleteCoach(Coach entity) throws Exception;

    /**
        * Update an existing Coach entity
        *
        */
    public void updateCoach(Coach entity) throws Exception;

    /**
         * Load an existing Coach entity
         *
         */
    public Coach getCoach(Integer idCoach) throws Exception;

    public List<Coach> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Coach> findPageCoach(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCoach() throws Exception;

    public List<CoachDTO> getDataCoach() throws Exception;

    public void validateCoach(Coach coach) throws Exception;
}
