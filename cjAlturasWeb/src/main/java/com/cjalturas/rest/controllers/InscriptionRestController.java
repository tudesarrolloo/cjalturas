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

import com.cjalturas.dto.mapper.IInscriptionMapper;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.dto.InscriptionDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;


@RestController
@RequestMapping("/inscription")
public class InscriptionRestController {
  private static final Logger log = LoggerFactory.getLogger(InscriptionRestController.class);

  @Autowired
  private IBusinessDelegatorView businessDelegatorView;

  @Autowired
  private IInscriptionMapper inscriptionMapper;

  @PostMapping(value = "/saveInscription")
  public void saveInscription(@RequestBody InscriptionDTO inscriptionDTO) throws Exception {
    try {
      Inscription inscription = inscriptionMapper.inscriptionDTOToInscription(inscriptionDTO);

      businessDelegatorView.saveInscription(inscription);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @DeleteMapping(value = "/deleteInscription/{idInscription}")
  public void deleteInscription(@PathVariable("idInscription") Integer idInscription) throws Exception {
    try {
      Inscription inscription = businessDelegatorView.getInscription(idInscription);

      businessDelegatorView.deleteInscription(inscription);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @PutMapping(value = "/updateInscription/")
  public void updateInscription(@RequestBody InscriptionDTO inscriptionDTO) throws Exception {
    try {
      Inscription inscription = inscriptionMapper.inscriptionDTOToInscription(inscriptionDTO);

      businessDelegatorView.updateInscription(inscription);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @GetMapping(value = "/getDataInscription")
  public List<InscriptionDTO> getDataInscription() throws Exception {
    try {
      return businessDelegatorView.getDataInscription();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @GetMapping(value = "/getInscription/{idInscription}")
  public InscriptionDTO getInscription(@PathVariable("idInscription") Integer idInscription) throws Exception {
    try {
      Inscription inscription = businessDelegatorView.getInscription(idInscription);

      return inscriptionMapper.inscriptionToInscriptionDTO(inscription);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }
}
