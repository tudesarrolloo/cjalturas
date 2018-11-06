package com.cjalturas.dataaccess.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.cjalturas.dataaccess.api.HibernateDaoImpl;
import com.cjalturas.model.Certificate;


/**
 * A data access object (DAO) providing persistence and search support for Status entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type of transaction control.
 *
 * @see lidis.Status
 */
@Scope("singleton")
@Repository("CertificateDAO")
public class CertificateDAO extends HibernateDaoImpl<Certificate, Integer> implements ICertificateDAO {
  private static final Logger log = LoggerFactory.getLogger(CertificateDAO.class);

  @Resource
  private SessionFactory sessionFactory;

  public static ICertificateDAO getFromApplicationContext(ApplicationContext ctx) {
    return (ICertificateDAO) ctx.getBean("CertificateDAO");
  }
}
