package com.cjalturas.dataaccess.dao;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.cjalturas.dataaccess.api.HibernateDaoImpl;
import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;


/**
 * A data access object (DAO) providing persistence and search support for Group entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type of transaction control.
 *
 * @see lidis.Group
 */
@Scope("singleton")
@Repository("GroupDAO")
public class GroupDAO extends HibernateDaoImpl<Group, Integer> implements IGroupDAO {
  private static final Logger log = LoggerFactory.getLogger(GroupDAO.class);

  @Resource
  private SessionFactory sessionFactory;

  public static IGroupDAO getFromApplicationContext(ApplicationContext ctx) {
    return (IGroupDAO) ctx.getBean("GroupDAO");
  }
  
  public Inscription findInscription(Integer idGroup,Integer idLearner) {
    try {
      String queryString = "from Inscription i where i.learner.idLearner= :idLearner and i.group.idGroup= :idGroup";
      Query queryObject = sessionFactory.getCurrentSession().createQuery(queryString);
      queryObject.setParameter("idLearner", idLearner);
      queryObject.setParameter("idGroup", idGroup);
      return (Inscription) queryObject.getSingleResult();
    } catch (NoResultException nr) {
      return null;
    } catch (RuntimeException re) {
      log.error("No se pudo encontrar la inscripción del aprendiz al grupo.", re);
      throw re;
    }
  }
}
