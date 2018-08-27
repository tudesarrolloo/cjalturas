package com.cjalturas.dataaccess.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.cjalturas.dataaccess.api.HibernateDaoImpl;
import com.cjalturas.model.Economicsector;


/**
 * A data access object (DAO) providing persistence and search support for Economicsector entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the desired type of transaction control.
 *
 * @see lidis.Economicsector
 */
@Scope("singleton")
@Repository("EconomicsectorDAO")
public class EconomicsectorDAO extends HibernateDaoImpl<Economicsector, Integer> implements IEconomicsectorDAO {
  private static final Logger log = LoggerFactory.getLogger(EconomicsectorDAO.class);

  @Resource
  private SessionFactory sessionFactory;

  public static IEconomicsectorDAO getFromApplicationContext(ApplicationContext ctx) {
    return (IEconomicsectorDAO) ctx.getBean("EconomicsectorDAO");
  }

  @Override
  public Economicsector findByName(String name) {
    List<Economicsector> list = findByProperty("economicSector", name);
    if (list.isEmpty()) {
      return null;
    } else if (list.size() > 1) {
      log.error("Se encontró más de un sector económico con el nombre: " + name);
      throw new RuntimeException("Se encontró más de un sector económico con el nombre: " + name);
    }
    return (Economicsector) list.get(0);
  }
}
