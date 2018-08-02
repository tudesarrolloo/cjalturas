package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IPermitMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.PermitDTO;

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
@RequestMapping("/permit")
public class PermitRestController {
    private static final Logger log = LoggerFactory.getLogger(PermitRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPermitMapper permitMapper;

    @PostMapping(value = "/savePermit")
    public void savePermit(@RequestBody
    PermitDTO permitDTO) throws Exception {
        try {
            Permit permit = permitMapper.permitDTOToPermit(permitDTO);

            businessDelegatorView.savePermit(permit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePermit/{idPermit}")
    public void deletePermit(@PathVariable("idPermit")
    Integer idPermit) throws Exception {
        try {
            Permit permit = businessDelegatorView.getPermit(idPermit);

            businessDelegatorView.deletePermit(permit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePermit/")
    public void updatePermit(@RequestBody
    PermitDTO permitDTO) throws Exception {
        try {
            Permit permit = permitMapper.permitDTOToPermit(permitDTO);

            businessDelegatorView.updatePermit(permit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPermit")
    public List<PermitDTO> getDataPermit() throws Exception {
        try {
            return businessDelegatorView.getDataPermit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPermit/{idPermit}")
    public PermitDTO getPermit(@PathVariable("idPermit")
    Integer idPermit) throws Exception {
        try {
            Permit permit = businessDelegatorView.getPermit(idPermit);

            return permitMapper.permitToPermitDTO(permit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
