package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IEnterpriseMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.EnterpriseDTO;

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
@RequestMapping("/enterprise")
public class EnterpriseRestController {
    private static final Logger log = LoggerFactory.getLogger(EnterpriseRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IEnterpriseMapper enterpriseMapper;

    @PostMapping(value = "/saveEnterprise")
    public void saveEnterprise(@RequestBody
    EnterpriseDTO enterpriseDTO) throws Exception {
        try {
            Enterprise enterprise = enterpriseMapper.enterpriseDTOToEnterprise(enterpriseDTO);

            businessDelegatorView.saveEnterprise(enterprise);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteEnterprise/{idEnterprise}")
    public void deleteEnterprise(
        @PathVariable("idEnterprise")
    Integer idEnterprise) throws Exception {
        try {
            Enterprise enterprise = businessDelegatorView.getEnterprise(idEnterprise);

            businessDelegatorView.deleteEnterprise(enterprise);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateEnterprise/")
    public void updateEnterprise(@RequestBody
    EnterpriseDTO enterpriseDTO) throws Exception {
        try {
            Enterprise enterprise = enterpriseMapper.enterpriseDTOToEnterprise(enterpriseDTO);

            businessDelegatorView.updateEnterprise(enterprise);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataEnterprise")
    public List<EnterpriseDTO> getDataEnterprise() throws Exception {
        try {
            return businessDelegatorView.getDataEnterprise();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getEnterprise/{idEnterprise}")
    public EnterpriseDTO getEnterprise(
        @PathVariable("idEnterprise")
    Integer idEnterprise) throws Exception {
        try {
            Enterprise enterprise = businessDelegatorView.getEnterprise(idEnterprise);

            return enterpriseMapper.enterpriseToEnterpriseDTO(enterprise);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
