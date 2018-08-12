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
import com.cjalturas.model.Course;


/**
 * A data access object (DAO) providing persistence and search support for Course entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type of transaction control.
 *
 * @see lidis.Course
 */
@Scope("singleton")
@Repository("CourseDAO")
public class CourseDAO extends HibernateDaoImpl<Course, Integer> implements ICourseDAO {
  private static final Logger log = LoggerFactory.getLogger(CourseDAO.class);

  @Resource
  private SessionFactory sessionFactory;

  public static ICourseDAO getFromApplicationContext(ApplicationContext ctx) {
    return (ICourseDAO) ctx.getBean("CourseDAO");
  }

  @Override
  public Course findByCourseName(String courseName) {
    List<Course> listCourses = findByProperty("course", courseName);
    if (listCourses.isEmpty()) {
      return null;
    } else if (listCourses.size() > 1) {
      log.error("Se encontró más de un curso con el nombre: " + courseName);
      throw new RuntimeException("Se encontró más de un curso con el nombre: " + courseName);
    }
    return (Course) listCourses.get(0);
  }
}
