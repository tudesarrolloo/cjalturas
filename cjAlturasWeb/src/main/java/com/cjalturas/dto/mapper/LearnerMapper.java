package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Learner;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.LearnerDTO;

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
public class LearnerMapper implements ILearnerMapper {
  private static final Logger log = LoggerFactory.getLogger(LearnerMapper.class);

  /**
   * Logic injected by Spring that manages Economicsector entities
   *
   */
  @Autowired
  IEconomicsectorLogic logicEconomicsector1;

  /**
   * Logic injected by Spring that manages Enterprise entities
   *
   */
  @Autowired
  IEnterpriseLogic logicEnterprise2;

  /**
   * Logic injected by Spring that manages Person entities
   *
   */
  @Autowired
  IPersonLogic logicPerson3;

  @Transactional(readOnly = true)
  public LearnerDTO learnerToLearnerDTO(Learner learner) throws Exception {
    try {
      LearnerDTO learnerDTO = new LearnerDTO();

      learnerDTO.setIdLearner(learner.getIdLearner());

      if (learner.getEconomicsector() != null) {
        learnerDTO.setIdEconomicSector_Economicsector(learner.getEconomicsector().getIdEconomicSector());
        learnerDTO.setEconomicSector(learner.getEconomicsector());
      } else {
        learnerDTO.setIdEconomicSector_Economicsector(null);
      }

      if (learner.getEnterprise() != null) {
        learnerDTO.setIdEnterprise_Enterprise(learner.getEnterprise().getIdEnterprise());
        learnerDTO.setEnterprise(learner.getEnterprise());
      } else {
        learnerDTO.setIdEnterprise_Enterprise(null);
      }

      learnerDTO.setIdPerson_Person((learner.getPerson().getIdPerson() != null) ? learner.getPerson().getIdPerson() : null);
      learnerDTO.setPerson(learner.getPerson());

      return learnerDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Learner learnerDTOToLearner(LearnerDTO learnerDTO) throws Exception {
    try {
      Learner learner = new Learner();

      learner.setIdLearner(learnerDTO.getIdLearner());

      Economicsector economicsector = new Economicsector();

      if (learnerDTO.getIdEconomicSector_Economicsector() != null) {
        economicsector = logicEconomicsector1.getEconomicsector(learnerDTO.getIdEconomicSector_Economicsector());
      }

      if (economicsector != null) {
        learner.setEconomicsector(economicsector);
      }

      Enterprise enterprise = new Enterprise();

      if (learnerDTO.getIdEnterprise_Enterprise() != null) {
        enterprise = logicEnterprise2.getEnterprise(learnerDTO.getIdEnterprise_Enterprise());
      }

      if (enterprise != null) {
        learner.setEnterprise(enterprise);
      }

      Person person = new Person();

      if (learnerDTO.getIdPerson_Person() != null) {
        person = logicPerson3.getPerson(learnerDTO.getIdPerson_Person());
      }

      if (person != null) {
        learner.setPerson(person);
      }

      return learner;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<LearnerDTO> listLearnerToListLearnerDTO(List<Learner> listLearner) throws Exception {
    try {
      List<LearnerDTO> learnerDTOs = new ArrayList<LearnerDTO>();

      for (Learner learner : listLearner) {
        LearnerDTO learnerDTO = learnerToLearnerDTO(learner);

        learnerDTOs.add(learnerDTO);
      }

      return learnerDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Learner> listLearnerDTOToListLearner(List<LearnerDTO> listLearnerDTO) throws Exception {
    try {
      List<Learner> listLearner = new ArrayList<Learner>();

      for (LearnerDTO learnerDTO : listLearnerDTO) {
        Learner learner = learnerDTOToLearner(learnerDTO);

        listLearner.add(learner);
      }

      return listLearner;
    } catch (Exception e) {
      throw e;
    }
  }
}
