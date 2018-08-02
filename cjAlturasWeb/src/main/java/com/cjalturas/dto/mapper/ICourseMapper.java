package com.cjalturas.dto.mapper;

import com.cjalturas.model.Course;
import com.cjalturas.model.dto.CourseDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ICourseMapper {
    public CourseDTO courseToCourseDTO(Course course) throws Exception;

    public Course courseDTOToCourse(CourseDTO courseDTO)
        throws Exception;

    public List<CourseDTO> listCourseToListCourseDTO(List<Course> courses)
        throws Exception;

    public List<Course> listCourseDTOToListCourse(List<CourseDTO> courseDTOs)
        throws Exception;
}
