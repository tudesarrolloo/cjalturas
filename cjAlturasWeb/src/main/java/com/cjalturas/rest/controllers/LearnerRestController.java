package com.cjalturas.rest.controllers;

import java.util.List;

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

import com.cjalturas.dto.mapper.ILearnerMapper;
import com.cjalturas.model.Learner;
import com.cjalturas.model.dto.LearnerDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;


@RestController
@RequestMapping("/learner")
public class LearnerRestController {
  private static final Logger log = LoggerFactory.getLogger(LearnerRestController.class);

  @Autowired
  private IBusinessDelegatorView businessDelegatorView;

  @Autowired
  private ILearnerMapper learnerMapper;

  @PostMapping(value = "/saveLearner")
  public void saveLearner(@RequestBody LearnerDTO learnerDTO) throws Exception {
    try {
      Learner learner = learnerMapper.learnerDTOToLearner(learnerDTO);

      businessDelegatorView.saveLearner(learner);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @DeleteMapping(value = "/deleteLearner/{idLearner}")
  public void deleteLearner(@PathVariable("idLearner") Integer idLearner) throws Exception {
    try {
      Learner learner = businessDelegatorView.getLearner(idLearner);

      businessDelegatorView.deleteLearner(learner);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @PutMapping(value = "/updateLearner/")
  public void updateLearner(@RequestBody LearnerDTO learnerDTO) throws Exception {
    try {
      Learner learner = learnerMapper.learnerDTOToLearner(learnerDTO);

      businessDelegatorView.updateLearner(learner);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @GetMapping(value = "/getDataLearner")
  public List<LearnerDTO> getDataLearner() throws Exception {
    try {
      return businessDelegatorView.getDataLearner();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @GetMapping(value = "/getLearner/{idLearner}")
  public LearnerDTO getLearner(@PathVariable("idLearner") Integer idLearner) throws Exception {
    try {
      Learner learner = businessDelegatorView.getLearner(idLearner);

      return learnerMapper.learnerToLearnerDTO(learner);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }
}
