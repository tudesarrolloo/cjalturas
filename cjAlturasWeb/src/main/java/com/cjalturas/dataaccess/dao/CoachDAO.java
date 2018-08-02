package com.cjalturas.dataaccess.dao;

import com.cjalturas.dataaccess.api.HibernateDaoImpl;

import com.cjalturas.model.Coach;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * Coach entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.Coach
 */
@Scope("singleton")
@Repository("CoachDAO")
public class CoachDAO extends HibernateDaoImpl<Coach, Integer>
    implements ICoachDAO {
    private static final Logger log = LoggerFactory.getLogger(CoachDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ICoachDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ICoachDAO) ctx.getBean("CoachDAO");
    }
}
