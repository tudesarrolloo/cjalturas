package com.cjalturas.model.control;

import java.util.List;

import com.cjalturas.model.Learner;
import com.cjalturas.model.dto.LearnerDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface ILearnerLogic {
  public List<Learner> getLearner() throws Exception;

  /**
   * Save an new Learner entity
   */
  public void saveLearner(Learner entity) throws Exception;

  /**
   * Delete an existing Learner entity
   *
   */
  public void deleteLearner(Learner entity) throws Exception;

  /**
   * Update an existing Learner entity
   *
   */
  public void updateLearner(Learner entity) throws Exception;

  /**
   * Load an existing Learner entity
   *
   */
  public Learner getLearner(Integer idLearner) throws Exception;

  public List<Learner> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

  public List<Learner> findPageLearner(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

  public Long findTotalNumberLearner() throws Exception;

  public List<LearnerDTO> getDataLearner() throws Exception;

  public void validateLearner(Learner learner) throws Exception;
}
