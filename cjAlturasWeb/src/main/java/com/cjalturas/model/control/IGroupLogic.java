package com.cjalturas.model.control;

import java.util.List;

import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.Learner;
import com.cjalturas.model.dto.GroupDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IGroupLogic {
  public List<Group> getGroup() throws Exception;

  /**
   * Save an new Group entity
   */
  public void saveGroup(Group entity) throws Exception;

  /**
   * Delete an existing Group entity
   *
   */
  public void deleteGroup(Group entity) throws Exception;

  /**
   * Update an existing Group entity
   *
   */
  public void updateGroup(Group entity) throws Exception;

  /**
   * Load an existing Group entity
   *
   */
  public Group getGroup(Integer idGroup) throws Exception;

  public List<Group> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

  public List<Group> findPageGroup(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

  public Long findTotalNumberGroup() throws Exception;

  public List<GroupDTO> getDataGroup() throws Exception;

  public void validateGroup(Group group) throws Exception;
  
  /**
   * Encuentra una inscripción de un aprendiz a un grupo.
   * @param idGroup identificación del grupo.
   * @param idLearner identificación del aprendiz que se desea buscar en el grupo.
   * @return instancia de la inscripción del aprendiz al grupo o null en caso de que no exista una inscripción.
   */
  public Inscription findInscription(Integer idGroup,Integer idLearner);
}
