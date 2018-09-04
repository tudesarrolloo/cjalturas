package com.cjalturas.model.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cjalturas.dataaccess.dao.ICoachDAO;
import com.cjalturas.dataaccess.dao.ILearnerDAO;
import com.cjalturas.dataaccess.dao.IPersonDAO;
import com.cjalturas.dataaccess.dao.IUserDAO;
import com.cjalturas.dto.mapper.IPersonMapper;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Learner;
import com.cjalturas.model.Person;
import com.cjalturas.model.User;
import com.cjalturas.model.dto.PersonDTO;
import com.cjalturas.utilities.Utilities;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("PersonLogic")
public class PersonLogic implements IPersonLogic {
  private static final Logger log = LoggerFactory.getLogger(PersonLogic.class);

  /**
   * DAO injected by Spring that manages Person entities
   *
   */
  @Autowired
  private IPersonDAO personDAO;

  @Autowired
  private IPersonMapper personMapper;

  @Autowired
  private Validator validator;

  /**
   * DAO injected by Spring that manages Coach entities
   *
   */
  @Autowired
  private ICoachDAO coachDAO;

  /**
   * DAO injected by Spring that manages Learner entities
   *
   */
  @Autowired
  private ILearnerDAO learnerDAO;

  /**
   * DAO injected by Spring that manages User entities
   *
   */
  @Autowired
  private IUserDAO userDAO;

  public void validatePerson(Person person) throws Exception {
    try {
      Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();

        for (ConstraintViolation<Person> constraintViolation : constraintViolations) {
          strMessage.append(constraintViolation.getPropertyPath().toString());
          strMessage.append(" - ");
          strMessage.append(constraintViolation.getMessage());
          strMessage.append(". \n");
        }

        throw new Exception(strMessage.toString());
      }
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Person> getPerson() throws Exception {
    log.debug("finding all Person instances");

    List<Person> list = new ArrayList<Person>();

    try {
      list = personDAO.findAll();
    } catch (Exception e) {
      log.error("finding all Person failed", e);
      throw new ZMessManager().new GettingException(ZMessManager.ALL + "Person");
    } finally {
    }

    return list;
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void savePerson(Person entity) throws Exception {
    log.debug("saving Person instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Person");
      }

//      validatePerson(entity);

      if (entity.getIdPerson() != null &&  getPerson(entity.getIdPerson()) != null) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }

      personDAO.save(entity);

      log.debug("save Person successful");
    } catch (Exception e) {
      log.error("save Person failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deletePerson(Person entity) throws Exception {
    log.debug("deleting Person instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Person");
    }

    if (entity.getIdPerson() == null) {
      throw new ZMessManager().new EmptyFieldException("idPerson");
    }

    List<Coach> coaches = null;
    List<Learner> learners = null;
    List<User> users = null;

    try {
      coaches = coachDAO.findByProperty("person.idPerson", entity.getIdPerson());

      if (Utilities.validationsList(coaches) == true) {
        throw new ZMessManager().new DeletingException("coaches");
      }

      learners = learnerDAO.findByProperty("person.idPerson", entity.getIdPerson());

      if (Utilities.validationsList(learners) == true) {
        throw new ZMessManager().new DeletingException("learners");
      }

      users = userDAO.findByProperty("person.idPerson", entity.getIdPerson());

      if (Utilities.validationsList(users) == true) {
        throw new ZMessManager().new DeletingException("users");
      }

      personDAO.delete(entity);

      log.debug("delete Person successful");
    } catch (Exception e) {
      log.error("delete Person failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void updatePerson(Person entity) throws Exception {
    log.debug("updating Person instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Person");
      }

      validatePerson(entity);

      personDAO.update(entity);

      log.debug("update Person successful");
    } catch (Exception e) {
      log.error("update Person failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = true)
  public List<PersonDTO> getDataPerson() throws Exception {
    try {
      List<Person> person = personDAO.findAll();

      List<PersonDTO> personDTO = new ArrayList<PersonDTO>();

      for (Person personTmp : person) {
        PersonDTO personDTO2 = personMapper.personToPersonDTO(personTmp);
        personDTO.add(personDTO2);
      }

      return personDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Person getPerson(Integer idPerson) throws Exception {
    log.debug("getting Person instance");

    Person entity = null;

    try {
      entity = personDAO.findById(idPerson);
    } catch (Exception e) {
      log.error("get Person failed", e);
      throw new ZMessManager().new FindingException("Person");
    } finally {
    }

    return entity;
  }

  @Transactional(readOnly = true)
  public List<Person> findPagePerson(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    List<Person> entity = null;

    try {
      entity = personDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
    } catch (Exception e) {
      throw new ZMessManager().new FindingException("Person Count");
    } finally {
    }

    return entity;
  }

  @Transactional(readOnly = true)
  public Long findTotalNumberPerson() throws Exception {
    Long entity = null;

    try {
      entity = personDAO.count();
    } catch (Exception e) {
      throw new ZMessManager().new FindingException("Person Count");
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
  public List<Person> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    List<Person> list = new ArrayList<Person>();
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
      list = personDAO.findByCriteria(where);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    } finally {
    }

    return list;
  }
}
