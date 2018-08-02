package com.cjalturas.presentation.businessDelegate;

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
import com.cjalturas.model.control.CoachLogic;
import com.cjalturas.model.control.CourseLogic;
import com.cjalturas.model.control.EconomicsectorLogic;
import com.cjalturas.model.control.EnterpriseLogic;
import com.cjalturas.model.control.GroupLogic;
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
import com.cjalturas.model.control.InscriptionLogic;
import com.cjalturas.model.control.LearnerLogic;
import com.cjalturas.model.control.PermitLogic;
import com.cjalturas.model.control.PersonLogic;
import com.cjalturas.model.control.RolLogic;
import com.cjalturas.model.control.StatusLogic;
import com.cjalturas.model.control.UserLogic;
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

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IBusinessDelegatorView {
    public List<Coach> getCoach() throws Exception;

    public void saveCoach(Coach entity) throws Exception;

    public void deleteCoach(Coach entity) throws Exception;

    public void updateCoach(Coach entity) throws Exception;

    public Coach getCoach(Integer idCoach) throws Exception;

    public List<Coach> findByCriteriaInCoach(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Coach> findPageCoach(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCoach() throws Exception;

    public List<CoachDTO> getDataCoach() throws Exception;

    public void validateCoach(Coach coach) throws Exception;

    public List<Course> getCourse() throws Exception;

    public void saveCourse(Course entity) throws Exception;

    public void deleteCourse(Course entity) throws Exception;

    public void updateCourse(Course entity) throws Exception;

    public Course getCourse(Integer idCourse) throws Exception;

    public List<Course> findByCriteriaInCourse(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Course> findPageCourse(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberCourse() throws Exception;

    public List<CourseDTO> getDataCourse() throws Exception;

    public void validateCourse(Course course) throws Exception;

    public List<Economicsector> getEconomicsector() throws Exception;

    public void saveEconomicsector(Economicsector entity)
        throws Exception;

    public void deleteEconomicsector(Economicsector entity)
        throws Exception;

    public void updateEconomicsector(Economicsector entity)
        throws Exception;

    public Economicsector getEconomicsector(Integer idEconomicSector)
        throws Exception;

    public List<Economicsector> findByCriteriaInEconomicsector(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<Economicsector> findPageEconomicsector(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEconomicsector() throws Exception;

    public List<EconomicsectorDTO> getDataEconomicsector()
        throws Exception;

    public void validateEconomicsector(Economicsector economicsector)
        throws Exception;

    public List<Enterprise> getEnterprise() throws Exception;

    public void saveEnterprise(Enterprise entity) throws Exception;

    public void deleteEnterprise(Enterprise entity) throws Exception;

    public void updateEnterprise(Enterprise entity) throws Exception;

    public Enterprise getEnterprise(Integer idEnterprise)
        throws Exception;

    public List<Enterprise> findByCriteriaInEnterprise(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Enterprise> findPageEnterprise(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberEnterprise() throws Exception;

    public List<EnterpriseDTO> getDataEnterprise() throws Exception;

    public void validateEnterprise(Enterprise enterprise)
        throws Exception;

    public List<Group> getGroup() throws Exception;

    public void saveGroup(Group entity) throws Exception;

    public void deleteGroup(Group entity) throws Exception;

    public void updateGroup(Group entity) throws Exception;

    public Group getGroup(Integer idGroup) throws Exception;

    public List<Group> findByCriteriaInGroup(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Group> findPageGroup(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberGroup() throws Exception;

    public List<GroupDTO> getDataGroup() throws Exception;

    public void validateGroup(Group group) throws Exception;

    public List<Inscription> getInscription() throws Exception;

    public void saveInscription(Inscription entity) throws Exception;

    public void deleteInscription(Inscription entity) throws Exception;

    public void updateInscription(Inscription entity) throws Exception;

    public Inscription getInscription(Integer idInscription)
        throws Exception;

    public List<Inscription> findByCriteriaInInscription(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Inscription> findPageInscription(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberInscription() throws Exception;

    public List<InscriptionDTO> getDataInscription() throws Exception;

    public void validateInscription(Inscription inscription)
        throws Exception;

    public List<Learner> getLearner() throws Exception;

    public void saveLearner(Learner entity) throws Exception;

    public void deleteLearner(Learner entity) throws Exception;

    public void updateLearner(Learner entity) throws Exception;

    public Learner getLearner(Integer idLearner) throws Exception;

    public List<Learner> findByCriteriaInLearner(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Learner> findPageLearner(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberLearner() throws Exception;

    public List<LearnerDTO> getDataLearner() throws Exception;

    public void validateLearner(Learner learner) throws Exception;

    public List<Permit> getPermit() throws Exception;

    public void savePermit(Permit entity) throws Exception;

    public void deletePermit(Permit entity) throws Exception;

    public void updatePermit(Permit entity) throws Exception;

    public Permit getPermit(Integer idPermit) throws Exception;

    public List<Permit> findByCriteriaInPermit(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Permit> findPagePermit(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPermit() throws Exception;

    public List<PermitDTO> getDataPermit() throws Exception;

    public void validatePermit(Permit permit) throws Exception;

    public List<Person> getPerson() throws Exception;

    public void savePerson(Person entity) throws Exception;

    public void deletePerson(Person entity) throws Exception;

    public void updatePerson(Person entity) throws Exception;

    public Person getPerson(Integer idPerson) throws Exception;

    public List<Person> findByCriteriaInPerson(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Person> findPagePerson(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberPerson() throws Exception;

    public List<PersonDTO> getDataPerson() throws Exception;

    public void validatePerson(Person person) throws Exception;

    public List<Rol> getRol() throws Exception;

    public void saveRol(Rol entity) throws Exception;

    public void deleteRol(Rol entity) throws Exception;

    public void updateRol(Rol entity) throws Exception;

    public Rol getRol(String codeRol) throws Exception;

    public List<Rol> findByCriteriaInRol(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Rol> findPageRol(String sortColumnName, boolean sortAscending,
        int startRow, int maxResults) throws Exception;

    public Long findTotalNumberRol() throws Exception;

    public List<RolDTO> getDataRol() throws Exception;

    public void validateRol(Rol rol) throws Exception;

    public List<Status> getStatus() throws Exception;

    public void saveStatus(Status entity) throws Exception;

    public void deleteStatus(Status entity) throws Exception;

    public void updateStatus(Status entity) throws Exception;

    public Status getStatus(String code) throws Exception;

    public List<Status> findByCriteriaInStatus(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Status> findPageStatus(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberStatus() throws Exception;

    public List<StatusDTO> getDataStatus() throws Exception;

    public void validateStatus(Status status) throws Exception;

    public List<User> getUser() throws Exception;

    public void saveUser(User entity) throws Exception;

    public void deleteUser(User entity) throws Exception;

    public void updateUser(User entity) throws Exception;

    public User getUser(Integer idUser) throws Exception;

    public List<User> findByCriteriaInUser(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<User> findPageUser(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberUser() throws Exception;

    public List<UserDTO> getDataUser() throws Exception;

    public void validateUser(User user) throws Exception;
}
