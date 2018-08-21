package com.cjalturas.model.control;

import java.util.List;

import com.cjalturas.model.Status;
import com.cjalturas.model.dto.StatusDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IStatusLogic {
  public List<Status> getStatus() throws Exception;

  /**
   * Save an new Status entity
   */
  public void saveStatus(Status entity) throws Exception;

  /**
   * Delete an existing Status entity
   *
   */
  public void deleteStatus(Status entity) throws Exception;

  /**
   * Update an existing Status entity
   *
   */
  public void updateStatus(Status entity) throws Exception;

  /**
   * Load an existing Status entity
   *
   */
  public Status getStatus(String code) throws Exception;

  public List<Status> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

  public List<Status> findPageStatus(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

  public Long findTotalNumberStatus() throws Exception;

  public List<StatusDTO> getDataStatus() throws Exception;

  public void validateStatus(Status status) throws Exception;
}
