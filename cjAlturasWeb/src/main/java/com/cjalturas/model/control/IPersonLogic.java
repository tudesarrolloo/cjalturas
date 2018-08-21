package com.cjalturas.model.control;

import java.util.List;

import com.cjalturas.model.Person;
import com.cjalturas.model.dto.PersonDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IPersonLogic {
  public List<Person> getPerson() throws Exception;

  /**
   * Save an new Person entity
   */
  public void savePerson(Person entity) throws Exception;

  /**
   * Delete an existing Person entity
   *
   */
  public void deletePerson(Person entity) throws Exception;

  /**
   * Update an existing Person entity
   *
   */
  public void updatePerson(Person entity) throws Exception;

  /**
   * Load an existing Person entity
   *
   */
  public Person getPerson(Integer idPerson) throws Exception;

  public List<Person> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

  public List<Person> findPagePerson(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

  public Long findTotalNumberPerson() throws Exception;

  public List<PersonDTO> getDataPerson() throws Exception;

  public void validatePerson(Person person) throws Exception;
}
