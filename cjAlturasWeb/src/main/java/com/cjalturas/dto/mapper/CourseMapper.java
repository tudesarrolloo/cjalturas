package com.cjalturas.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cjalturas.model.Course;
import com.cjalturas.model.dto.CourseDTO;


/**
 * Mapea el resultado de la entidad Curso a su equivalente en dto.
 * @author Edison
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
      courseDTO.setCourse((course.getCourse() != null) ? course.getCourse() : null);
      courseDTO.setIntensity((course.getIntensity() != null) ? course.getIntensity() : null);
      courseDTO.setValidityDaysCertificate((course.getValidityDaysCertificate() != null) ? course.getValidityDaysCertificate() : null);

      return courseDTO;
    } catch (Exception e) {
      log.error("Error convirtiendo curso a su equivalente en dto");
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Course courseDTOToCourse(CourseDTO courseDTO) throws Exception {
    try {
      Course course = new Course();

      course.setIdCourse(courseDTO.getIdCourse());
      course.setCourse((courseDTO.getCourse() != null) ? courseDTO.getCourse() : null);
      course.setIntensity((courseDTO.getIntensity() != null) ? courseDTO.getIntensity() : null);
      course.setValidityDaysCertificate((courseDTO.getValidityDaysCertificate() != null) ? courseDTO.getValidityDaysCertificate() : null);

      return course;
    } catch (Exception e) {
      log.error("Error convirtiendo dto a su equivalente en curso");
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<CourseDTO> listCourseToListCourseDTO(List<Course> listCourse) throws Exception {
    try {
      List<CourseDTO> courseDTOs = new ArrayList<CourseDTO>();

      for (Course course : listCourse) {
        CourseDTO courseDTO = courseToCourseDTO(course);

        courseDTOs.add(courseDTO);
      }

      return courseDTOs;
    } catch (Exception e) {
      log.error("Error convirtiendo lista de cursos a su equivalente en lista de cursos dto");
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Course> listCourseDTOToListCourse(List<CourseDTO> listCourseDTO) throws Exception {
    try {
      List<Course> listCourse = new ArrayList<Course>();

      for (CourseDTO courseDTO : listCourseDTO) {
        Course course = courseDTOToCourse(courseDTO);

        listCourse.add(course);
      }

      return listCourse;
    } catch (Exception e) {
      log.error("Error convirtiendo lista de cursos dto a su equivalente en lista de cursos");
      throw e;
    }
  }
}
