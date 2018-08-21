package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.InscriptionDTO;

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
public class InscriptionMapper implements IInscriptionMapper {
  private static final Logger log = LoggerFactory.getLogger(InscriptionMapper.class);

  /**
   * Logic injected by Spring that manages Group entities
   *
   */
  @Autowired
  IGroupLogic logicGroup1;

  /**
   * Logic injected by Spring that manages Learner entities
   *
   */
  @Autowired
  ILearnerLogic logicLearner2;

  /**
   * Logic injected by Spring that manages Status entities
   *
   */
  @Autowired
  IStatusLogic logicStatus3;

  @Transactional(readOnly = true)
  public InscriptionDTO inscriptionToInscriptionDTO(Inscription inscription) throws Exception {
    try {
      InscriptionDTO inscriptionDTO = new InscriptionDTO();

      inscriptionDTO.setIdInscription(inscription.getIdInscription());
      inscriptionDTO.setDateCertification(inscription.getDateCertification());
      inscriptionDTO.setDateInscription(inscription.getDateInscription());
      inscriptionDTO.setIdGroup_Group((inscription.getGroup().getIdGroup() != null) ? inscription.getGroup().getIdGroup() : null);
      inscriptionDTO.setIdLearner_Learner((inscription.getLearner().getIdLearner() != null) ? inscription.getLearner().getIdLearner() : null);
      inscriptionDTO.setCode_Status((inscription.getStatus().getCode() != null) ? inscription.getStatus().getCode() : null);

      return inscriptionDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Inscription inscriptionDTOToInscription(InscriptionDTO inscriptionDTO) throws Exception {
    try {
      Inscription inscription = new Inscription();

      inscription.setIdInscription(inscriptionDTO.getIdInscription());
      inscription.setDateCertification(inscriptionDTO.getDateCertification());
      inscription.setDateInscription(inscriptionDTO.getDateInscription());

      Group group = new Group();

      if (inscriptionDTO.getIdGroup_Group() != null) {
        group = logicGroup1.getGroup(inscriptionDTO.getIdGroup_Group());
      }

      if (group != null) {
        inscription.setGroup(group);
      }

      Learner learner = new Learner();

      if (inscriptionDTO.getIdLearner_Learner() != null) {
        learner = logicLearner2.getLearner(inscriptionDTO.getIdLearner_Learner());
      }

      if (learner != null) {
        inscription.setLearner(learner);
      }

      Status status = new Status();

      if (inscriptionDTO.getCode_Status() != null) {
        status = logicStatus3.getStatus(inscriptionDTO.getCode_Status());
      }

      if (status != null) {
        inscription.setStatus(status);
      }

      return inscription;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<InscriptionDTO> listInscriptionToListInscriptionDTO(List<Inscription> listInscription) throws Exception {
    try {
      List<InscriptionDTO> inscriptionDTOs = new ArrayList<InscriptionDTO>();

      for (Inscription inscription : listInscription) {
        InscriptionDTO inscriptionDTO = inscriptionToInscriptionDTO(inscription);

        inscriptionDTOs.add(inscriptionDTO);
      }

      return inscriptionDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Inscription> listInscriptionDTOToListInscription(List<InscriptionDTO> listInscriptionDTO) throws Exception {
    try {
      List<Inscription> listInscription = new ArrayList<Inscription>();

      for (InscriptionDTO inscriptionDTO : listInscriptionDTO) {
        Inscription inscription = inscriptionDTOToInscription(inscriptionDTO);

        listInscription.add(inscription);
      }

      return listInscription;
    } catch (Exception e) {
      throw e;
    }
  }
}
