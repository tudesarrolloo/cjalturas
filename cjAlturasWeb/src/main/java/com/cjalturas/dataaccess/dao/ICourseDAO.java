package com.cjalturas.dataaccess.dao;

import com.cjalturas.dataaccess.api.Dao;

import com.cjalturas.model.Course;


/**
 * Interfaz DAO para las acciones relacionadas con los cursos.
 * @author Edison
 */
public interface ICourseDAO extends Dao<Course, Integer> {
	
	public Course findByCourseName(String courseName);
}
