package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IEconomicsectorMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.EconomicsectorDTO;

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
@RequestMapping("/economicsector")
public class EconomicsectorRestController {
    private static final Logger log = LoggerFactory.getLogger(EconomicsectorRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IEconomicsectorMapper economicsectorMapper;

    @PostMapping(value = "/saveEconomicsector")
    public void saveEconomicsector(
        @RequestBody
    EconomicsectorDTO economicsectorDTO) throws Exception {
        try {
            Economicsector economicsector = economicsectorMapper.economicsectorDTOToEconomicsector(economicsectorDTO);

            businessDelegatorView.saveEconomicsector(economicsector);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteEconomicsector/{idEconomicSector}")
    public void deleteEconomicsector(
        @PathVariable("idEconomicSector")
    Integer idEconomicSector) throws Exception {
        try {
            Economicsector economicsector = businessDelegatorView.getEconomicsector(idEconomicSector);

            businessDelegatorView.deleteEconomicsector(economicsector);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateEconomicsector/")
    public void updateEconomicsector(
        @RequestBody
    EconomicsectorDTO economicsectorDTO) throws Exception {
        try {
            Economicsector economicsector = economicsectorMapper.economicsectorDTOToEconomicsector(economicsectorDTO);

            businessDelegatorView.updateEconomicsector(economicsector);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataEconomicsector")
    public List<EconomicsectorDTO> getDataEconomicsector()
        throws Exception {
        try {
            return businessDelegatorView.getDataEconomicsector();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getEconomicsector/{idEconomicSector}")
    public EconomicsectorDTO getEconomicsector(
        @PathVariable("idEconomicSector")
    Integer idEconomicSector) throws Exception {
        try {
            Economicsector economicsector = businessDelegatorView.getEconomicsector(idEconomicSector);

            return economicsectorMapper.economicsectorToEconomicsectorDTO(economicsector);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
