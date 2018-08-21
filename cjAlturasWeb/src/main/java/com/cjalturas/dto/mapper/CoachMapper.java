package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Coach;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.CoachDTO;

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
public class CoachMapper implements ICoachMapper {
  private static final Logger log = LoggerFactory.getLogger(CoachMapper.class);

  /**
   * Logic injected by Spring that manages Person entities
   *
   */
  @Autowired
  IPersonLogic logicPerson1;

  @Transactional(readOnly = true)
  public CoachDTO coachToCoachDTO(Coach coach) throws Exception {
    try {
      CoachDTO coachDTO = new CoachDTO();

      coachDTO.setIdCoach(coach.getIdCoach());
      coachDTO.setCharge((coach.getCharge() != null) ? coach.getCharge() : null);
      coachDTO.setLicenseSst((coach.getLicenseSst() != null) ? coach.getLicenseSst() : null);
      coachDTO.setSign((coach.getSign() != null) ? coach.getSign() : null);
      coachDTO.setIdPerson_Person((coach.getPerson().getIdPerson() != null) ? coach.getPerson().getIdPerson() : null);

      return coachDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Coach coachDTOToCoach(CoachDTO coachDTO) throws Exception {
    try {
      Coach coach = new Coach();

      coach.setIdCoach(coachDTO.getIdCoach());
      coach.setCharge((coachDTO.getCharge() != null) ? coachDTO.getCharge() : null);
      coach.setLicenseSst((coachDTO.getLicenseSst() != null) ? coachDTO.getLicenseSst() : null);
      coach.setSign((coachDTO.getSign() != null) ? coachDTO.getSign() : null);

      Person person = new Person();

      if (coachDTO.getIdPerson_Person() != null) {
        person = logicPerson1.getPerson(coachDTO.getIdPerson_Person());
      }

      if (person != null) {
        coach.setPerson(person);
      }

      return coach;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<CoachDTO> listCoachToListCoachDTO(List<Coach> listCoach) throws Exception {
    try {
      List<CoachDTO> coachDTOs = new ArrayList<CoachDTO>();

      for (Coach coach : listCoach) {
        CoachDTO coachDTO = coachToCoachDTO(coach);

        coachDTOs.add(coachDTO);
      }

      return coachDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Coach> listCoachDTOToListCoach(List<CoachDTO> listCoachDTO) throws Exception {
    try {
      List<Coach> listCoach = new ArrayList<Coach>();

      for (CoachDTO coachDTO : listCoachDTO) {
        Coach coach = coachDTOToCoach(coachDTO);

        listCoach.add(coach);
      }

      return listCoach;
    } catch (Exception e) {
      throw e;
    }
  }
}
