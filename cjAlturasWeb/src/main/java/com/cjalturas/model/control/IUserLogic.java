package com.cjalturas.model.control;

import java.util.List;

import com.cjalturas.model.User;
import com.cjalturas.model.dto.UserDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IUserLogic {
  public List<User> getUser() throws Exception;

  /**
   * Save an new User entity
   */
  public void saveUser(User entity) throws Exception;

  /**
   * Delete an existing User entity
   *
   */
  public void deleteUser(User entity) throws Exception;

  /**
   * Update an existing User entity
   *
   */
  public void updateUser(User entity) throws Exception;

  /**
   * Load an existing User entity
   *
   */
  public User getUser(Integer idUser) throws Exception;

  public List<User> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

  public List<User> findPageUser(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

  public Long findTotalNumberUser() throws Exception;

  public List<UserDTO> getDataUser() throws Exception;

  public void validateUser(User user) throws Exception;
}
