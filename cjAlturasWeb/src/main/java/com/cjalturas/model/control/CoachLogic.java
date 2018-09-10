package com.cjalturas.model.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cjalturas.dataaccess.dao.ICoachDAO;
import com.cjalturas.dataaccess.dao.IGroupDAO;
import com.cjalturas.dto.mapper.ICoachMapper;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Group;
import com.cjalturas.model.Person;
import com.cjalturas.model.dto.CoachDTO;
import com.cjalturas.utilities.Utilities;
import com.cjalturas.validation.ConstraintMessagesTransformer;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("CoachLogic")
public class CoachLogic implements ICoachLogic {
  private static final Logger log = LoggerFactory.getLogger(CoachLogic.class);

  /**
   * DAO injected by Spring that manages Coach entities
   *
   */
  @Autowired
  private ICoachDAO coachDAO;

  @Autowired
  private ICoachMapper coachMapper;

  @Autowired
  private Validator validator;

  /**
   * DAO injected by Spring that manages Group entities
   *
   */
  @Autowired
  private IGroupDAO groupDAO;

  /**
   * Logic injected by Spring that manages Person entities
   *
   */
  @Autowired
  IPersonLogic logicPerson1;

  public void validateCoach(Coach coach) throws Exception {
    try {
      Set<ConstraintViolation<Coach>> constraintViolations = validator.validate(coach);
      Set<ConstraintViolation<Person>> constraintViolationsPerson = validator.validate(coach.getPerson());
      
      ConstraintMessagesTransformer transformerConstraints = new ConstraintMessagesTransformer();
      transformerConstraints.transform(constraintViolationsPerson);
      transformerConstraints.transform(constraintViolations);
      String validationMessages = transformerConstraints.getValidationMessage();
      
      if (StringUtils.isNotBlank(validationMessages)) {
        throw new Exception(validationMessages);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Coach> getCoach() throws Exception {
    log.debug("finding all Coach instances");

    List<Coach> list = new ArrayList<Coach>();

    try {
      list = coachDAO.findAll();
    } catch (Exception e) {
      log.error("finding all Coach failed", e);
      throw new ZMessManager().new GettingException(ZMessManager.ALL + "Coach");
    } finally {
    }

    return list;
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void saveCoach(Coach entity) throws Exception {
    log.debug("saving Coach instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Coach");
      }

      validateCoach(entity);

      if (entity.getIdCoach() != null && getCoach(entity.getIdCoach()) != null) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }

      logicPerson1.savePerson(entity.getPerson());
      coachDAO.save(entity);
      

      log.debug("save Coach successful");
    } catch (Exception e) {
      log.error("save Coach failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteCoach(Coach entity) throws Exception {
    log.debug("deleting Coach instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Coach");
    }

    if (entity.getIdCoach() == null) {
      throw new ZMessManager().new EmptyFieldException("idCoach");
    }

    List<Group> groups = null;

    try {
      groups = groupDAO.findByProperty("coach.idCoach", entity.getIdCoach());

      if (Utilities.validationsList(groups) == true) {
        throw new ZMessManager().new DeletingException("groups");
      }

      logicPerson1.deletePerson(entity.getPerson());
      coachDAO.delete(entity);

      log.debug("delete Coach successful");
    } catch (Exception e) {
      log.error("delete Coach failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void updateCoach(Coach entity) throws Exception {
    log.debug("updating Coach instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Coach");
      }

      validateCoach(entity);
      
      logicPerson1.updatePerson(entity.getPerson());
      coachDAO.update(entity);

      log.debug("update Coach successful");
    } catch (Exception e) {
      log.error("update Coach failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = true)
  public List<CoachDTO> getDataCoach() throws Exception {
    try {
      List<Coach> coach = coachDAO.findAll();

      List<CoachDTO> coachDTO = new ArrayList<CoachDTO>();

      for (Coach coachTmp : coach) {
        CoachDTO coachDTO2 = coachMapper.coachToCoachDTO(coachTmp);
        coachDTO.add(coachDTO2);
      }
      return coachDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Coach getCoach(Integer idCoach) throws Exception {
    log.debug("getting Coach instance");

    Coach entity = null;

    try {
      entity = coachDAO.findById(idCoach);
    } catch (Exception e) {
      log.error("get Coach failed", e);
      throw new ZMessManager().new FindingException("Coach");
    } finally {
    }

    return entity;
  }

  @Transactional(readOnly = true)
  public List<Coach> findPageCoach(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    List<Coach> entity = null;

    try {
      entity = coachDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
    } catch (Exception e) {
      throw new ZMessManager().new FindingException("Coach Count");
    } finally {
    }

    return entity;
  }

  @Transactional(readOnly = true)
  public Long findTotalNumberCoach() throws Exception {
    Long entity = null;

    try {
      entity = coachDAO.count();
    } catch (Exception e) {
      throw new ZMessManager().new FindingException("Coach Count");
    } finally {
    }

    return entity;
  }

  /**
   *
   * @param varibles este arreglo debera tener:
   *
   * [0] = String variable = (String) varibles[i]; representa como se llama la variable en el pojo
   *
   * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el valor necesita o no ''(comillas simples)usado para campos de tipo string
   *
   * [2] = Object value = varibles[i + 2]; representa el valor que se va a buscar en la BD
   *
   * [3] = String comparator = (String) varibles[i + 3]; representa que tipo de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
   * en este campo iria el tipo de comparador que quiero si es = o <>
   *
   * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1 busqueda en un campo, si se ponen mas pues el continuara buscando en lo que se le
   * ingresen en los otros 4
   *
   *
   * @param variablesBetween
   *
   * la diferencia son estas dos posiciones
   *
   * [0] = String variable = (String) varibles[j]; la variable ne la BD que va a ser buscada en un rango
   *
   * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
   *
   * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
   *
   * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1 ejemplo: a comparator1 1 and a < 5
   *
   * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el original: a > 1 and a < 5) *
   * @param variablesBetweenDates(en este caso solo para mysql) [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a una
   * fecha
   *
   * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser dates)
   *
   * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser dates)
   *
   * esto hace un between entre las dos fechas.
   *
   * @return lista con los objetos que se necesiten
   * @throws Exception
   */
  @Transactional(readOnly = true)
  public List<Coach> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    List<Coach> list = new ArrayList<Coach>();
    String where = new String();
    String tempWhere = new String();

    if (variables != null) {
      for (int i = 0; i < variables.length; i++) {
        if ((variables[i] != null) && (variables[i + 1] != null) && (variables[i + 2] != null) && (variables[i + 3] != null)) {
          String variable = (String) variables[i];
          Boolean booVariable = (Boolean) variables[i + 1];
          Object value = variables[i + 2];
          String comparator = (String) variables[i + 3];

          if (booVariable.booleanValue()) {
            tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " \'" + value + "\' )")
                : (tempWhere + " AND (model." + variable + " " + comparator + " \'" + value + "\' )");
          } else {
            tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " " + comparator + " " + value + " )")
                : (tempWhere + " AND (model." + variable + " " + comparator + " " + value + " )");
          }
        }

        i = i + 3;
      }
    }

    if (variablesBetween != null) {
      for (int j = 0; j < variablesBetween.length; j++) {
        if ((variablesBetween[j] != null) && (variablesBetween[j + 1] != null) && (variablesBetween[j + 2] != null) && (variablesBetween[j + 3] != null)
            && (variablesBetween[j + 4] != null)) {
          String variable = (String) variablesBetween[j];
          Object value = variablesBetween[j + 1];
          Object value2 = variablesBetween[j + 2];
          String comparator1 = (String) variablesBetween[j + 3];
          String comparator2 = (String) variablesBetween[j + 4];
          tempWhere = (tempWhere.length() == 0)
              ? ("(" + value + " " + comparator1 + " " + variable + " and " + variable + " " + comparator2 + " " + value2 + " )")
              : (tempWhere + " AND (" + value + " " + comparator1 + " " + variable + " and " + variable + " " + comparator2 + " " + value2 + " )");
        }

        j = j + 4;
      }
    }

    if (variablesBetweenDates != null) {
      for (int k = 0; k < variablesBetweenDates.length; k++) {
        if ((variablesBetweenDates[k] != null) && (variablesBetweenDates[k + 1] != null) && (variablesBetweenDates[k + 2] != null)) {
          String variable = (String) variablesBetweenDates[k];
          Object object1 = variablesBetweenDates[k + 1];
          Object object2 = variablesBetweenDates[k + 2];
          String value = null;
          String value2 = null;

          try {
            Date date1 = (Date) object1;
            Date date2 = (Date) object2;
            value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
            value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
          } catch (Exception e) {
            list = null;
            throw e;
          }

          tempWhere = (tempWhere.length() == 0) ? ("(model." + variable + " between " + value + " and " + value2 + ")")
              : (tempWhere + " AND (model." + variable + " between " + value + " and " + value2 + ")");
        }

        k = k + 2;
      }
    }

    if (tempWhere.length() == 0) {
      where = null;
    } else {
      where = "(" + tempWhere + ")";
    }

    try {
      list = coachDAO.findByCriteria(where);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    } finally {
    }

    return list;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Coach> findCoachByProperty(String propertyName, Object propertyValue) throws Exception {
    log.debug("Buscando entrenador o lista de entrenador por una propiedad");
    try {
      return coachDAO.findByProperty(propertyName, propertyValue);
    } catch (Exception e) {
      log.error("Falló la búsqueda de entrenadores", e);
      throw new ZMessManager().new FindingException("Coach");
    }
  }

}
