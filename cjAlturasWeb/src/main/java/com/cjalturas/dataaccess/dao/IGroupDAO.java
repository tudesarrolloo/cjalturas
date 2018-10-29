package com.cjalturas.dataaccess.dao;

import com.cjalturas.dataaccess.api.Dao;

import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.Learner;


/**
 * Interface for GroupDAO.
 *
 */
public interface IGroupDAO extends Dao<Group, Integer> {
  
  public Inscription findInscription(Integer idGroup,Integer idLearner);
}
