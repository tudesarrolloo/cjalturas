package com.cjalturas.dataaccess.dao;

import com.cjalturas.dataaccess.api.Dao;

import com.cjalturas.model.Economicsector;


/**
 * Interface for EconomicsectorDAO.
 *
 */
public interface IEconomicsectorDAO extends Dao<Economicsector, Integer> {
  
  /**
   * Se busca un sector económico por su nombre.
   * @param name nombre del sector ecnonómico.
   * @return instancia del sector económico.
   */
  Economicsector findByName(String name);
}
