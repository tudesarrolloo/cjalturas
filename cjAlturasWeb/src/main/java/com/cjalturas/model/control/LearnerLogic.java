package com.cjalturas.model.control;

import com.cjalturas.dataaccess.dao.*;

import com.cjalturas.dto.mapper.ILearnerMapper;

import com.cjalturas.exceptions.*;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.LearnerDTO;

import com.cjalturas.utilities.Utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service("LearnerLogic")
public class LearnerLogic implements ILearnerLogic {
    private static final Logger log = LoggerFactory.getLogger(LearnerLogic.class);

    /**
     * DAO injected by Spring that manages Learner entities
     *
     */
    @Autowired
    private ILearnerDAO learnerDAO;
    @Autowired
    private ILearnerMapper learnerMapper;
    @Autowired
    private Validator validator;

    /**
    * DAO injected by Spring that manages Inscription entities
    *
    */
    @Autowired
    private IInscriptionDAO inscriptionDAO;

    /**
    * Logic injected by Spring that manages Economicsector entities
    *
    */
    @Autowired
    IEconomicsectorLogic logicEconomicsector1;

    /**
    * Logic injected by Spring that manages Enterprise entities
    *
    */
    @Autowired
    IEnterpriseLogic logicEnterprise2;

    /**
    * Logic injected by Spring that manages Person entities
    *
    */
    @Autowired
    IPersonLogic logicPerson3;

    public void validateLearner(Learner learner) throws Exception {
        try {
            Set<ConstraintViolation<Learner>> constraintViolations = validator.validate(learner);

            if (constraintViolations.size() > 0) {
                StringBuilder strMessage = new StringBuilder();

                for (ConstraintViolation<Learner> constraintViolation : constraintViolations) {
                    strMessage.append(constraintViolation.getPropertyPath()
                                                         .toString());
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
    public List<Learner> getLearner() throws Exception {
        log.debug("finding all Learner instances");

        List<Learner> list = new ArrayList<Learner>();

        try {
            list = learnerDAO.findAll();
        } catch (Exception e) {
            log.error("finding all Learner failed", e);
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "Learner");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveLearner(Learner entity) throws Exception {
        log.debug("saving Learner instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Learner");
            }

            validateLearner(entity);

            if (getLearner(entity.getIdLearner()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            learnerDAO.save(entity);

            log.debug("save Learner successful");
        } catch (Exception e) {
            log.error("save Learner failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteLearner(Learner entity) throws Exception {
        log.debug("deleting Learner instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Learner");
        }

        if (entity.getIdLearner() == null) {
            throw new ZMessManager().new EmptyFieldException("idLearner");
        }

        List<Inscription> inscriptions = null;

        try {
            inscriptions = inscriptionDAO.findByProperty("learner.idLearner",
                    entity.getIdLearner());

            if (Utilities.validationsList(inscriptions) == true) {
                throw new ZMessManager().new DeletingException("inscriptions");
            }

            learnerDAO.delete(entity);

            log.debug("delete Learner successful");
        } catch (Exception e) {
            log.error("delete Learner failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateLearner(Learner entity) throws Exception {
        log.debug("updating Learner instance");

        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("Learner");
            }

            validateLearner(entity);

            learnerDAO.update(entity);

            log.debug("update Learner successful");
        } catch (Exception e) {
            log.error("update Learner failed", e);
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<LearnerDTO> getDataLearner() throws Exception {
        try {
            List<Learner> learner = learnerDAO.findAll();

            List<LearnerDTO> learnerDTO = new ArrayList<LearnerDTO>();

            for (Learner learnerTmp : learner) {
                LearnerDTO learnerDTO2 = learnerMapper.learnerToLearnerDTO(learnerTmp);
                learnerDTO.add(learnerDTO2);
            }

            return learnerDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Learner getLearner(Integer idLearner) throws Exception {
        log.debug("getting Learner instance");

        Learner entity = null;

        try {
            entity = learnerDAO.findById(idLearner);
        } catch (Exception e) {
            log.error("get Learner failed", e);
            throw new ZMessManager().new FindingException("Learner");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<Learner> findPageLearner(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<Learner> entity = null;

        try {
            entity = learnerDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Learner Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberLearner() throws Exception {
        Long entity = null;

        try {
            entity = learnerDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Learner Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<Learner> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<Learner> list = new ArrayList<Learner>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
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

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between " + value +
                        " and " + value2 + ")")
                        : (tempWhere + " AND (model." + variable + " between " +
                        value + " and " + value2 + ")");
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
            list = learnerDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
