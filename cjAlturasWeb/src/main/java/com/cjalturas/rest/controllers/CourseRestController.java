package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.ICourseMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.CourseDTO;

import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseRestController {
    private static final Logger log = LoggerFactory.getLogger(CourseRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private ICourseMapper courseMapper;

    @PostMapping(value = "/saveCourse")
    public void saveCourse(@RequestBody
    CourseDTO courseDTO) throws Exception {
        try {
            Course course = courseMapper.courseDTOToCourse(courseDTO);

            businessDelegatorView.saveCourse(course);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteCourse/{idCourse}")
    public void deleteCourse(@PathVariable("idCourse")
    Integer idCourse) throws Exception {
        try {
            Course course = businessDelegatorView.getCourse(idCourse);

            businessDelegatorView.deleteCourse(course);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateCourse/")
    public void updateCourse(@RequestBody
    CourseDTO courseDTO) throws Exception {
        try {
            Course course = courseMapper.courseDTOToCourse(courseDTO);

            businessDelegatorView.updateCourse(course);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataCourse")
    public List<CourseDTO> getDataCourse() throws Exception {
        try {
            return businessDelegatorView.getDataCourse();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getCourse/{idCourse}")
    public CourseDTO getCourse(@PathVariable("idCourse")
    Integer idCourse) throws Exception {
        try {
            Course course = businessDelegatorView.getCourse(idCourse);

            return courseMapper.courseToCourseDTO(course);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
