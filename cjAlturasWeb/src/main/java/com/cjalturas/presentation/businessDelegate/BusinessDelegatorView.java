package com.cjalturas.presentation.businessDelegate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cjalturas.model.Certificate;
import com.cjalturas.model.Coach;
import com.cjalturas.model.Course;
import com.cjalturas.model.Economicsector;
import com.cjalturas.model.Enterprise;
import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.Learner;
import com.cjalturas.model.Permit;
import com.cjalturas.model.Person;
import com.cjalturas.model.Rol;
import com.cjalturas.model.Status;
import com.cjalturas.model.User;
import com.cjalturas.model.control.ICoachLogic;
import com.cjalturas.model.control.ICourseLogic;
import com.cjalturas.model.control.IEconomicsectorLogic;
import com.cjalturas.model.control.IEnterpriseLogic;
import com.cjalturas.model.control.IGroupLogic;
import com.cjalturas.model.control.IInscriptionLogic;
import com.cjalturas.model.control.ILearnerLogic;
import com.cjalturas.model.control.IPermitLogic;
import com.cjalturas.model.control.IPersonLogic;
import com.cjalturas.model.control.IRolLogic;
import com.cjalturas.model.control.IStatusLogic;
import com.cjalturas.model.control.IUserLogic;
import com.cjalturas.model.dto.CertificateValidationDto;
import com.cjalturas.model.dto.CoachDTO;
import com.cjalturas.model.dto.CourseDTO;
import com.cjalturas.model.dto.EconomicsectorDTO;
import com.cjalturas.model.dto.EnterpriseDTO;
import com.cjalturas.model.dto.GroupDTO;
import com.cjalturas.model.dto.InscriptionDTO;
import com.cjalturas.model.dto.LearnerDTO;
import com.cjalturas.model.dto.PermitDTO;
import com.cjalturas.model.dto.PersonDTO;
import com.cjalturas.model.dto.RolDTO;
import com.cjalturas.model.dto.StatusDTO;
import com.cjalturas.model.dto.UserDTO;


/**
 * Use a Business Delegate to reduce coupling between presentation-tier clients and business services. The Business Delegate hides the underlying implementation
 * details of the business service, such as lookup and access details of the EJB architecture.
 *
 * The Business Delegate acts as a client-side business abstraction; it provides an abstraction for, and thus hides, the implementation of the business
 * services. Using a Business Delegate reduces the coupling between presentation-tier clients and the system's business services. Depending on the
 * implementation strategy, the Business Delegate may shield clients from possible volatility in the implementation of the business service API. Potentially,
 * this reduces the number of changes that must be made to the presentation-tier client code when the business service API or its underlying implementation
 * changes.
 *
 * However, interface methods in the Business Delegate may still require modification if the underlying business service API changes. Admittedly, though, it is
 * more likely that changes will be made to the business service rather than to the Business Delegate.
 *
 * Often, developers are skeptical when a design goal such as abstracting the business layer causes additional upfront work in return for future gains. However,
 * using this pattern or its strategies results in only a small amount of additional upfront work and provides considerable benefits. The main benefit is hiding
 * the details of the underlying service. For example, the client can become transparent to naming and lookup services. The Business Delegate also handles the
 * exceptions from the business services, such as java.rmi.Remote exceptions, Java Messages Service (JMS) exceptions and so on. The Business Delegate may
 * intercept such service level exceptions and generate application level exceptions instead. Application level exceptions are easier to handle by the clients,
 * and may be user friendly. The Business Delegate may also transparently perform any retry or recovery operations necessary in the event of a service failure
 * without exposing the client to the problem until it is determined that the problem is not resolvable. These gains present a compelling reason to use the
 * pattern.
 *
 * Another benefit is that the delegate may cache results and references to remote business services. Caching can significantly improve performance, because it
 * limits unnecessary and potentially costly round trips over the network.
 *
 * A Business Delegate uses a component called the Lookup Service. The Lookup Service is responsible for hiding the underlying implementation details of the
 * business service lookup code. The Lookup Service may be written as part of the Delegate, but we recommend that it be implemented as a separate component, as
 * outlined in the Service Locator pattern (See "Service Locator" on page 368.)
 *
 * When the Business Delegate is used with a Session Facade, typically there is a one-to-one relationship between the two. This one-to-one relationship exists
 * because logic that might have been encapsulated in a Business Delegate relating to its interaction with multiple business services (creating a one-to-many
 * relationship) will often be factored back into a Session Facade.
 *
 * Finally, it should be noted that this pattern could be used to reduce coupling between other tiers, not simply the presentation and the business tiers.
 *
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("BusinessDelegatorView")
public class BusinessDelegatorView implements IBusinessDelegatorView {
  private static final Logger log = LoggerFactory.getLogger(BusinessDelegatorView.class);

  @Autowired
  private ICoachLogic coachLogic;

  @Autowired
  private ICourseLogic courseLogic;

  @Autowired
  private IEconomicsectorLogic economicsectorLogic;

  @Autowired
  private IEnterpriseLogic enterpriseLogic;

  @Autowired
  private IGroupLogic groupLogic;

  @Autowired
  private IInscriptionLogic inscriptionLogic;

  @Autowired
  private ILearnerLogic learnerLogic;

  @Autowired
  private IPermitLogic permitLogic;

  @Autowired
  private IPersonLogic personLogic;

  @Autowired
  private IRolLogic rolLogic;

  @Autowired
  private IStatusLogic statusLogic;

  @Autowired
  private IUserLogic userLogic;

  public List<Coach> getCoach() throws Exception {
    return coachLogic.getCoach();
  }

  public void saveCoach(Coach entity) throws Exception {
    coachLogic.saveCoach(entity);
  }

  public void deleteCoach(Coach entity) throws Exception {
    coachLogic.deleteCoach(entity);
  }

  public void updateCoach(Coach entity) throws Exception {
    coachLogic.updateCoach(entity);
  }

  public Coach getCoach(Integer idCoach) throws Exception {
    Coach coach = null;

    try {
      coach = coachLogic.getCoach(idCoach);
    } catch (Exception e) {
      throw e;
    }

    return coach;
  }
  
  @Override
  public List<Coach> findCoachByProperty(String propertyName, String valueProperty) throws Exception {
    try {
      return coachLogic.findCoachByProperty(propertyName, valueProperty);
    } catch (Exception e) {
      throw e;
    }
  }
  
  @Override
  public List<Learner> findLearnerByProperty(String propertyName, String valueProperty) throws Exception {
    try {
      return learnerLogic.findLearnerByProperty(propertyName, valueProperty);
    } catch (Exception e) {
      throw e;
    }
  }
  
  public List<Coach> findByCriteriaInCoach(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return coachLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Coach> findPageCoach(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return coachLogic.findPageCoach(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberCoach() throws Exception {
    return coachLogic.findTotalNumberCoach();
  }

  public List<CoachDTO> getDataCoach() throws Exception {
    return coachLogic.getDataCoach();
  }

  public void validateCoach(Coach coach) throws Exception {
    coachLogic.validateCoach(coach);
  }

  public List<Course> getCourse() throws Exception {
    return courseLogic.getCourse();
  }

  public void saveCourse(Course entity) throws Exception {
    courseLogic.saveCourse(entity);
  }

  public void deleteCourse(Course entity) throws Exception {
    courseLogic.deleteCourse(entity);
  }

  public void updateCourse(Course entity) throws Exception {
    courseLogic.updateCourse(entity);
  }

  public Course getCourse(Integer idCourse) throws Exception {
    Course course = null;

    try {
      course = courseLogic.getCourse(idCourse);
    } catch (Exception e) {
      throw e;
    }

    return course;
  }

  public List<Course> findByCriteriaInCourse(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return courseLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Course> findPageCourse(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return courseLogic.findPageCourse(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberCourse() throws Exception {
    return courseLogic.findTotalNumberCourse();
  }

  public List<CourseDTO> getDataCourse() throws Exception {
    return courseLogic.getDataCourse();
  }

  public void validateCourse(Course course) throws Exception {
    courseLogic.validateCourse(course);
  }

  public List<Economicsector> getEconomicsector() throws Exception {
    return economicsectorLogic.getEconomicsector();
  }

  public void saveEconomicsector(Economicsector entity) throws Exception {
    economicsectorLogic.saveEconomicsector(entity);
  }

  public void deleteEconomicsector(Economicsector entity) throws Exception {
    economicsectorLogic.deleteEconomicsector(entity);
  }

  public void updateEconomicsector(Economicsector entity) throws Exception {
    economicsectorLogic.updateEconomicsector(entity);
  }

  public Economicsector getEconomicsector(Integer idEconomicSector) throws Exception {
    Economicsector economicsector = null;

    try {
      economicsector = economicsectorLogic.getEconomicsector(idEconomicSector);
    } catch (Exception e) {
      throw e;
    }

    return economicsector;
  }

  public List<Economicsector> findByCriteriaInEconomicsector(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return economicsectorLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Economicsector> findPageEconomicsector(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return economicsectorLogic.findPageEconomicsector(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberEconomicsector() throws Exception {
    return economicsectorLogic.findTotalNumberEconomicsector();
  }

  public List<EconomicsectorDTO> getDataEconomicsector() throws Exception {
    return economicsectorLogic.getDataEconomicsector();
  }

  public void validateEconomicsector(Economicsector economicsector) throws Exception {
    economicsectorLogic.validateEconomicsector(economicsector);
  }

  public List<Enterprise> getEnterprise() throws Exception {
    return enterpriseLogic.getEnterprise();
  }

  public void saveEnterprise(Enterprise entity) throws Exception {
    enterpriseLogic.saveEnterprise(entity);
  }

  public void deleteEnterprise(Enterprise entity) throws Exception {
    enterpriseLogic.deleteEnterprise(entity);
  }

  public void updateEnterprise(Enterprise entity) throws Exception {
    enterpriseLogic.updateEnterprise(entity);
  }

  public Enterprise getEnterprise(Integer idEnterprise) throws Exception {
    Enterprise enterprise = null;

    try {
      enterprise = enterpriseLogic.getEnterprise(idEnterprise);
    } catch (Exception e) {
      throw e;
    }

    return enterprise;
  }

  @Override
  public List<Enterprise> findEnterpriseByProperty(String propertyName, Object value) throws Exception {
    try {
      return enterpriseLogic.findEnterpriseByProperty(propertyName, value);
    } catch (Exception e) {
      throw e;
    }
  }

  public List<Enterprise> findByCriteriaInEnterprise(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return enterpriseLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Enterprise> findPageEnterprise(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return enterpriseLogic.findPageEnterprise(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberEnterprise() throws Exception {
    return enterpriseLogic.findTotalNumberEnterprise();
  }

  public List<EnterpriseDTO> getDataEnterprise() throws Exception {
    return enterpriseLogic.getDataEnterprise();
  }

  public void validateEnterprise(Enterprise enterprise) throws Exception {
    enterpriseLogic.validateEnterprise(enterprise);
  }

  public List<Group> getGroup() throws Exception {
    return groupLogic.getGroup();
  }

  public void saveGroup(Group entity) throws Exception {
    groupLogic.saveGroup(entity);
  }

  public void deleteGroup(Group entity) throws Exception {
    groupLogic.deleteGroup(entity);
  }

  public void updateGroup(Group entity) throws Exception {
    groupLogic.updateGroup(entity);
  }

  public Group getGroup(Integer idGroup) throws Exception {
    Group group = null;

    try {
      group = groupLogic.getGroup(idGroup);
    } catch (Exception e) {
      throw e;
    }

    return group;
  }

  public List<Group> findByCriteriaInGroup(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return groupLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Group> findPageGroup(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return groupLogic.findPageGroup(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberGroup() throws Exception {
    return groupLogic.findTotalNumberGroup();
  }

  public List<GroupDTO> getDataGroup() throws Exception {
    return groupLogic.getDataGroup();
  }

  public void validateGroup(Group group) throws Exception {
    groupLogic.validateGroup(group);
  }

  public List<Inscription> getInscription() throws Exception {
    return inscriptionLogic.getInscription();
  }

  public void saveInscription(Inscription entity) throws Exception {
    inscriptionLogic.saveInscription(entity);
  }

  public void deleteInscription(Inscription entity) throws Exception {
    inscriptionLogic.deleteInscription(entity);
  }

  public void updateInscription(Inscription entity) throws Exception {
    inscriptionLogic.updateInscription(entity);
  }

  public Inscription getInscription(Integer idInscription) throws Exception {
    Inscription inscription = null;

    try {
      inscription = inscriptionLogic.getInscription(idInscription);
    } catch (Exception e) {
      throw e;
    }

    return inscription;
  }

  public List<Inscription> findByCriteriaInInscription(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return inscriptionLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Inscription> findPageInscription(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return inscriptionLogic.findPageInscription(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberInscription() throws Exception {
    return inscriptionLogic.findTotalNumberInscription();
  }

  public List<InscriptionDTO> getDataInscription() throws Exception {
    return inscriptionLogic.getDataInscription();
  }

  public void validateInscription(Inscription inscription) throws Exception {
    inscriptionLogic.validateInscription(inscription);
  }

  public List<Learner> getLearner() throws Exception {
    return learnerLogic.getLearner();
  }

  public void saveLearner(Learner entity) throws Exception {
    learnerLogic.saveLearner(entity);
  }

  public void deleteLearner(Learner entity) throws Exception {
    learnerLogic.deleteLearner(entity);
  }

  public void updateLearner(Learner entity) throws Exception {
    learnerLogic.updateLearner(entity);
  }

  public Learner getLearner(Integer idLearner) throws Exception {
    Learner learner = null;

    try {
      learner = learnerLogic.getLearner(idLearner);
    } catch (Exception e) {
      throw e;
    }

    return learner;
  }

  public List<Learner> findByCriteriaInLearner(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return learnerLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Learner> findPageLearner(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return learnerLogic.findPageLearner(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberLearner() throws Exception {
    return learnerLogic.findTotalNumberLearner();
  }

  public List<LearnerDTO> getDataLearner() throws Exception {
    return learnerLogic.getDataLearner();
  }

  public void validateLearner(Learner learner) throws Exception {
    learnerLogic.validateLearner(learner);
  }

  public List<Permit> getPermit() throws Exception {
    return permitLogic.getPermit();
  }

  public void savePermit(Permit entity) throws Exception {
    permitLogic.savePermit(entity);
  }

  public void deletePermit(Permit entity) throws Exception {
    permitLogic.deletePermit(entity);
  }

  public void updatePermit(Permit entity) throws Exception {
    permitLogic.updatePermit(entity);
  }

  public Permit getPermit(Integer idPermit) throws Exception {
    Permit permit = null;

    try {
      permit = permitLogic.getPermit(idPermit);
    } catch (Exception e) {
      throw e;
    }

    return permit;
  }

  public List<Permit> findByCriteriaInPermit(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return permitLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Permit> findPagePermit(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return permitLogic.findPagePermit(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberPermit() throws Exception {
    return permitLogic.findTotalNumberPermit();
  }

  public List<PermitDTO> getDataPermit() throws Exception {
    return permitLogic.getDataPermit();
  }

  public void validatePermit(Permit permit) throws Exception {
    permitLogic.validatePermit(permit);
  }

  public List<Person> getPerson() throws Exception {
    return personLogic.getPerson();
  }

  public void savePerson(Person entity) throws Exception {
    personLogic.savePerson(entity);
  }

  public void deletePerson(Person entity) throws Exception {
    personLogic.deletePerson(entity);
  }

  public void updatePerson(Person entity) throws Exception {
    personLogic.updatePerson(entity);
  }

  public Person getPerson(Integer idPerson) throws Exception {
    Person person = null;

    try {
      person = personLogic.getPerson(idPerson);
    } catch (Exception e) {
      throw e;
    }

    return person;
  }

  public List<Person> findByCriteriaInPerson(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return personLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Person> findPagePerson(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return personLogic.findPagePerson(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberPerson() throws Exception {
    return personLogic.findTotalNumberPerson();
  }

  public List<PersonDTO> getDataPerson() throws Exception {
    return personLogic.getDataPerson();
  }

  public void validatePerson(Person person) throws Exception {
    personLogic.validatePerson(person);
  }

  public List<Rol> getRol() throws Exception {
    return rolLogic.getRol();
  }

  public void saveRol(Rol entity) throws Exception {
    rolLogic.saveRol(entity);
  }

  public void deleteRol(Rol entity) throws Exception {
    rolLogic.deleteRol(entity);
  }

  public void updateRol(Rol entity) throws Exception {
    rolLogic.updateRol(entity);
  }

  public Rol getRol(String codeRol) throws Exception {
    Rol rol = null;

    try {
      rol = rolLogic.getRol(codeRol);
    } catch (Exception e) {
      throw e;
    }

    return rol;
  }

  public List<Rol> findByCriteriaInRol(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return rolLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Rol> findPageRol(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return rolLogic.findPageRol(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberRol() throws Exception {
    return rolLogic.findTotalNumberRol();
  }

  public List<RolDTO> getDataRol() throws Exception {
    return rolLogic.getDataRol();
  }

  public void validateRol(Rol rol) throws Exception {
    rolLogic.validateRol(rol);
  }

  public List<Status> getStatus() throws Exception {
    return statusLogic.getStatus();
  }

  public void saveStatus(Status entity) throws Exception {
    statusLogic.saveStatus(entity);
  }

  public void deleteStatus(Status entity) throws Exception {
    statusLogic.deleteStatus(entity);
  }

  public void updateStatus(Status entity) throws Exception {
    statusLogic.updateStatus(entity);
  }

  public Status getStatus(String code) throws Exception {
    Status status = null;

    try {
      status = statusLogic.getStatus(code);
    } catch (Exception e) {
      throw e;
    }

    return status;
  }

  public List<Status> findByCriteriaInStatus(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return statusLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<Status> findPageStatus(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return statusLogic.findPageStatus(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberStatus() throws Exception {
    return statusLogic.findTotalNumberStatus();
  }

  public List<StatusDTO> getDataStatus() throws Exception {
    return statusLogic.getDataStatus();
  }

  public void validateStatus(Status status) throws Exception {
    statusLogic.validateStatus(status);
  }

  public List<User> getUser() throws Exception {
    return userLogic.getUser();
  }

  public void saveUser(User entity) throws Exception {
    userLogic.saveUser(entity);
  }

  public void deleteUser(User entity) throws Exception {
    userLogic.deleteUser(entity);
  }

  public void updateUser(User entity) throws Exception {
    userLogic.updateUser(entity);
  }

  public User getUser(Integer idUser) throws Exception {
    User user = null;

    try {
      user = userLogic.getUser(idUser);
    } catch (Exception e) {
      throw e;
    }

    return user;
  }

  public List<User> findByCriteriaInUser(Object[] variables, Object[] variablesBetween, Object[] variablesBetweenDates) throws Exception {
    return userLogic.findByCriteria(variables, variablesBetween, variablesBetweenDates);
  }

  public List<User> findPageUser(String sortColumnName, boolean sortAscending, int startRow, int maxResults) throws Exception {
    return userLogic.findPageUser(sortColumnName, sortAscending, startRow, maxResults);
  }

  public Long findTotalNumberUser() throws Exception {
    return userLogic.findTotalNumberUser();
  }

  public List<UserDTO> getDataUser() throws Exception {
    return userLogic.getDataUser();
  }

  public void validateUser(User user) throws Exception {
    userLogic.validateUser(user);
  }

  @Override
  public Object getParam(String id) {
    return params.get(id);
  }

  @Override
  public void setParam(String id, Object value) {
    params.put(id, value);
  }

  @Override
  public Certificate certificate(Inscription entity) throws Exception {
    return inscriptionLogic.certificate(entity);
  }

  @Override
  public Certificate getCertificate(Integer idInscription) throws Exception {
    return inscriptionLogic.getCertificate(idInscription);
  }

  @Override
  public CertificateValidationDto validateCertificate(String codeCertificate, String document) throws Exception {
    return inscriptionLogic.validateCertificate(codeCertificate, document);
  }

  @Override
  public List<Inscription> getAllInscriptions() {
    return inscriptionLogic.getAllInscriptions();
  }

}
