package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Group;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.GroupDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Component
@Scope("singleton")
public class GroupMapper implements IGroupMapper {
  private static final Logger log = LoggerFactory.getLogger(GroupMapper.class);

  /**
   * Logic injected by Spring that manages Coach entities
   *
   */
  @Autowired
  ICoachLogic logicCoach1;

  /**
   * Logic injected by Spring that manages Course entities
   *
   */
  @Autowired
  ICourseLogic logicCourse2;

  @Transactional(readOnly = true)
  public GroupDTO groupToGroupDTO(Group group) throws Exception {
    try {
      GroupDTO groupDTO = new GroupDTO();

      groupDTO.setIdGroup(group.getIdGroup());
      groupDTO.setDescription(group.getDescription());
      groupDTO.setDateStart(group.getDateStart());
      groupDTO.setDateEnd(group.getDateEnd());
      groupDTO.setStatus(group.getStatus());
      groupDTO.setDaysCourse(group.getDaysCourse());
      groupDTO.setObservations((group.getObservations() != null) ? group.getObservations() : null);
      groupDTO.setLearnersInGroup(group.getInscriptions().size());

      if (group.getCoach() != null) {
        groupDTO.setIdCoach_Coach(group.getCoach().getIdCoach());
        groupDTO.setCoach(group.getCoach());
      } else {
        groupDTO.setIdCoach_Coach(null);
      }
      
      if (group.getCourse() != null) {
        groupDTO.setIdCourse_Course(group.getCourse().getIdCourse());
        groupDTO.setCourse(group.getCourse());
      } else {
        groupDTO.setIdCourse_Course(null);
      }

      return groupDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Group groupDTOToGroup(GroupDTO groupDTO) throws Exception {
    try {
      Group group = new Group();

      group.setIdGroup(groupDTO.getIdGroup());
      group.setDateStart(groupDTO.getDateStart());
      group.setObservations((groupDTO.getObservations() != null) ? groupDTO.getObservations() : null);
      group.setDaysCourse((groupDTO.getDaysCourse() != null) ? groupDTO.getDaysCourse() : null);

      Coach coach = new Coach();

      if (groupDTO.getIdCoach_Coach() != null) {
        coach = logicCoach1.getCoach(groupDTO.getIdCoach_Coach());
      }

      if (coach != null) {
        group.setCoach(coach);
      }

      Course course = new Course();

      if (groupDTO.getIdCourse_Course() != null) {
        course = logicCourse2.getCourse(groupDTO.getIdCourse_Course());
      }

      if (course != null) {
        group.setCourse(course);
      }

      return group;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<GroupDTO> listGroupToListGroupDTO(List<Group> listGroup) throws Exception {
    try {
      List<GroupDTO> groupDTOs = new ArrayList<GroupDTO>();

      for (Group group : listGroup) {
        GroupDTO groupDTO = groupToGroupDTO(group);

        groupDTOs.add(groupDTO);
      }

      return groupDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Group> listGroupDTOToListGroup(List<GroupDTO> listGroupDTO) throws Exception {
    try {
      List<Group> listGroup = new ArrayList<Group>();

      for (GroupDTO groupDTO : listGroupDTO) {
        Group group = groupDTOToGroup(groupDTO);

        listGroup.add(group);
      }

      return listGroup;
    } catch (Exception e) {
      throw e;
    }
  }
}
