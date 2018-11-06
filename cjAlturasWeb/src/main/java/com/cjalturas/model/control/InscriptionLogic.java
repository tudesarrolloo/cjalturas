package com.cjalturas.model.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cjalturas.dataaccess.dao.ICertificateDAO;
import com.cjalturas.dataaccess.dao.ICoachDAO;
import com.cjalturas.dataaccess.dao.IInscriptionDAO;
import com.cjalturas.date.DateProvider;
import com.cjalturas.dto.mapper.IInscriptionMapper;
import com.cjalturas.exceptions.CustomException;
import com.cjalturas.exceptions.ZMessManager;
import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Certificate;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Course;
import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.Learner;
import com.cjalturas.model.Person;
import com.cjalturas.model.Status;
import com.cjalturas.model.dto.InscriptionDTO;
import com.cjalturas.utilities.FormatUtils;
import com.cjalturas.utilities.Utilities;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("InscriptionLogic")
public class InscriptionLogic implements IInscriptionLogic {
  private static final Logger log = LoggerFactory.getLogger(InscriptionLogic.class);

  /**
   * DAO injected by Spring that manages Inscription entities
   *
   */
  @Autowired
  private IInscriptionDAO inscriptionDAO;
  
  @Autowired
  private ICoachDAO coachDAO;
  
  @Autowired
  private ICertificateDAO certificateDAO;


  @Autowired
  private IInscriptionMapper inscriptionMapper;

  @Autowired
  private Validator validator;

  /**
   * Logic injected by Spring that manages Group entities
   *
   */
  @Autowired
  IGroupLogic logicGroup1;

  /**
   * Logic injected by Spring that manages Learner entities
   *
   */
  @Autowired
  ILearnerLogic logicLearner2;

  /**
   * Logic injected by Spring that manages Status entities
   *
   */
  @Autowired
  IStatusLogic logicStatus3;

  public void validateInscription(Inscription inscription) throws Exception {
    try {
      Set<ConstraintViolation<Inscription>> constraintViolations = validator.validate(inscription);

      if (constraintViolations.size() > 0) {
        StringBuilder strMessage = new StringBuilder();

        for (ConstraintViolation<Inscription> constraintViolation : constraintViolations) {
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
  public List<Inscription> getInscription() throws Exception {
    log.debug("finding all Inscription instances");

    List<Inscription> list = new ArrayList<Inscription>();

    try {
      list = inscriptionDAO.findAll();
    } catch (Exception e) {
      log.error("finding all Inscription failed", e);
      throw new ZMessManager().new GettingException(ZMessManager.ALL + "Inscription");
    } finally {
    }

    return list;
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void saveInscription(Inscription entity) throws Exception {
    log.debug("saving Inscription instance");
    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Inscription");
      }

      validateInscription(entity);

      if (entity.getIdInscription() != null && getInscription(entity.getIdInscription()) != null) {
        throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
      }

      Learner learner = entity.getLearner();
      Group group = entity.getGroup();
      Inscription inscription = logicGroup1.findInscription(group.getIdGroup(), learner.getIdLearner());
      if (inscription != null) {
        String message = ApplicationMessages.getInstance().getMessage("inscription.learnerExistInGroup", learner.getPerson().getName(), group.getDescription());
        throw new CustomException(message, message, null);
      }
      inscriptionDAO.save(entity);
      log.debug("save Inscription successful");
    } catch (CustomException e) {
      throw e;
    } catch (Exception e) {
      log.error("save Inscription failed", e);
      Learner learner = entity.getLearner();
      Group group = entity.getGroup();
      throw new Exception(ApplicationMessages.getInstance().getMessage("inscription.save.failure", learner.getPerson().getName(), group.getDescription()));
    } finally {
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void deleteInscription(Inscription entity) throws Exception {
    log.debug("deleting Inscription instance");

    if (entity == null) {
      throw new ZMessManager().new NullEntityExcepcion("Inscription");
    }

    if (entity.getIdInscription() == null) {
      throw new ZMessManager().new EmptyFieldException("idInscription");
    }

    try {
      inscriptionDAO.delete(entity);

      log.debug("delete Inscription successful");
    } catch (Exception e) {
      log.error("delete Inscription failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void updateInscription(Inscription entity) throws Exception {
    log.debug("updating Inscription instance");

    try {
      if (entity == null) {
        throw new ZMessManager().new NullEntityExcepcion("Inscription");
      }

      validateInscription(entity);

      inscriptionDAO.update(entity);

      log.debug("update Inscription successful");
    } catch (Exception e) {
      log.error("update Inscription failed", e);
      throw e;
    } finally {
    }
  }

  @Transactional(readOnly = true)
  public List<InscriptionDTO> getDataInscription() throws Exception {
    try {
      List<Inscription> inscription = inscriptionDAO.findAll();

      List<InscriptionDTO> inscriptionDTO = new ArrayList<InscriptionDTO>();

      for (Inscription inscriptionTmp : inscription) {
        InscriptionDTO inscriptionDTO2 = inscriptionMapper.inscriptionToInscriptionDTO(inscriptionTmp);
        inscriptionDTO.add(inscriptionDTO2);
      }

      return inscriptionDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Inscription getInscription(Integer idInscription) throws Exception {
    log.debug("getting Inscription instance");

    Inscription entity = null;

    try {
      entity = inscriptionDAO.findById(idInscription);
    } catch (Exception e) {
      log.error("get Inscription failed", e);
      throw new ZMessManager().new FindingException("Inscription");
    } finally {
    }

    return entity;
  }

  @Transactional(readOnly = true)
  public List<Inscription> findPageInscription(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    List<Inscription> entity = null;

    try {
      entity = inscriptionDAO.findPage(sortColumnName, sortAscending, startRow, maxResults);
    } catch (Exception e) {
      throw new ZMessManager().new FindingException("Inscription Count");
    } finally {
    }

    return entity;
  }

  @Transactional(readOnly = true)
  public Long findTotalNumberInscription() throws Exception {
    Long entity = null;

    try {
      entity = inscriptionDAO.count();
    } catch (Exception e) {
      throw new ZMessManager().new FindingException("Inscription Count");
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
  public List<Inscription> findByCriteria(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    List<Inscription> list = new ArrayList<Inscription>();
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
      list = inscriptionDAO.findByCriteria(where);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    } finally {
    }

    return list;
  }

  @Override
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public Certificate certificate(Inscription entity) throws Exception {
    log.debug("Certificando una inscripción");
    try {
      if (entity == null) {
        String message = "No se recibió la información del aprendiz a certificar";
        throw new CustomException(message, message, null);
      }
      
      //Marcamos la inscripción como certificada
      entity.setStatus(Status.CERTIFICATE);
      inscriptionDAO.update(entity);
      
      //Generamos el certificado. 
      ApplicationMessages messages = ApplicationMessages.getInstance();
      Person learner = entity.getLearner().getPerson();
      Coach coach1 = entity.getGroup().getCoach();
      Person personCoach1 = coach1.getPerson();
      Course course = entity.getGroup().getCourse();
      
      Certificate certificate = new Certificate();
      certificate.setInscription(entity);
      
      Date currentDate = DateProvider.getInstance().getCurrentDate();
      certificate.setDate(FormatUtils.convertDate(currentDate, FormatUtils.CERTIFICATE_DATE));
      
      certificate.setCertification(messages.getMessage("certificate.certification"));
      certificate.setLearner(learner.getFullName());
      certificate.setLearnerDocument(learner.getDocumentType() + " " + learner.getDocument());
      certificate.setLevel(course.getCourse());
      certificate.setIntensity(course.getIntensity());
      certificate.setDays(entity.getGroup().getDaysCourse());
      certificate.setCity(messages.getMessage("certificate.city"));
      certificate.setInstructor1(personCoach1.getFullName());
      certificate.setInstructor1Charge(coach1.getCharge());
      certificate.setInstructor1Sign(coach1.getSign());
      
      Integer idCoach2= Integer.parseInt(messages.getMessage("certificate.instructor2"));
      Coach coach2 = coachDAO.findById(idCoach2);
      certificate.setInstructor2(coach2.getPerson().getFullName());
      certificate.setInstructor2Charge(coach2.getCharge());
      certificate.setInstructor2Sign(coach2.getSign());
      
      Date dateExpiration = DateUtils.addDays(currentDate, course.getValidityDaysCertificate());
      certificate.setDateExpiration(dateExpiration);
      certificateDAO.save(certificate);
      
      String code = String.format("%03d", certificate.getIdCertificate());
      certificate.setCode(messages.getMessage("certificate.code", code));
      
      certificateDAO.update(certificate);
      
      System.out.println(certificate.getLearner());
     
      log.debug("update Inscription successful");
      
      return certificate;
    } catch (Exception e) {
      log.error("update Inscription failed", e);
      throw e;
    } finally {
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Certificate getCertificate(Integer idInscription) throws Exception {
    log.debug("Buscando certificado de una inscripción ya certificada");
    try {
      List<Certificate> result = certificateDAO.findByProperty("inscription.idInscription", idInscription);
      if (result.size()>1) {
        throw new CustomException("Existe más de un certificado generado para el aprendiz en este grupo.");
      }
      if (result == null || result.isEmpty()) {
        throw new CustomException("No se encontró la información del certificado para un aprendiz que ya está certificado en este grupo.");
      }
      return result.get(0);
    } catch (Exception e) {
      log.error("get Inscription failed", e);
      throw new ZMessManager().new FindingException("Inscription");
    } finally {
    }
  }
}
