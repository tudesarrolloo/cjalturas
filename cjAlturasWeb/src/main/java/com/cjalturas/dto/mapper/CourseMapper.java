package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Course;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.CourseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class CourseMapper implements ICourseMapper {
    private static final Logger log = LoggerFactory.getLogger(CourseMapper.class);

    @Transactional(readOnly = true)
    public CourseDTO courseToCourseDTO(Course course) throws Exception {
        try {
            CourseDTO courseDTO = new CourseDTO();

            courseDTO.setIdCourse(course.getIdCourse());
            courseDTO.setCourse((course.getCourse() != null)
                ? course.getCourse() : null);

            return courseDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Course courseDTOToCourse(CourseDTO courseDTO)
        throws Exception {
        try {
            Course course = new Course();

            course.setIdCourse(courseDTO.getIdCourse());
            course.setCourse((courseDTO.getCourse() != null)
                ? courseDTO.getCourse() : null);

            return course;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> listCourseToListCourseDTO(List<Course> listCourse)
        throws Exception {
        try {
            List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();

            for (Course course : listCourse) {
                CourseDTO courseDTO = courseToCourseDTO(course);

                courseDTOs.add(courseDTO);
            }

            return courseDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Course> listCourseDTOToListCourse(List<CourseDTO> listCourseDTO)
        throws Exception {
        try {
            List<Course> listCourse = new ArrayList<Course>();

            for (CourseDTO courseDTO : listCourseDTO) {
                Course course = courseDTOToCourse(courseDTO);

                listCourse.add(course);
            }

            return listCourse;
        } catch (Exception e) {
            throw e;
        }
    }
}
