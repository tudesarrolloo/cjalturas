package com.cjalturas.model.control;

import java.util.List;

import com.cjalturas.model.Certificate;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.dto.InscriptionDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IInscriptionLogic {
  public List<Inscription> getInscription() throws Exception;

  /**
   * Save an new Inscription entity
   */
  public void saveInscription(Inscription entity) throws Exception;

  /**
   * Delete an existing Inscription entity
   *
   */
  public void deleteInscription(Inscription entity) throws Exception;

  /**
   * Update an existing Inscription entity
   *
   */
  public void updateInscription(Inscription entity) throws Exception;

  /**
   * Load an existing Inscription entity
   *
   */
  public Inscription getInscription(Integer idInscription) throws Exception;

  public List<Inscription> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception;

  public List<Inscription> findPageInscription(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception;

  public Long findTotalNumberInscription() throws Exception;

  public List<InscriptionDTO> getDataInscription() throws Exception;

  public void validateInscription(Inscription inscription) throws Exception;

  public Certificate certificate(Inscription entity) throws Exception;

  public Certificate getCertificate(Integer idInscription) throws Exception;
  
}
