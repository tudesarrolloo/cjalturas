package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IRolMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.RolDTO;

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
@RequestMapping("/rol")
public class RolRestController {
    private static final Logger log = LoggerFactory.getLogger(RolRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IRolMapper rolMapper;

    @PostMapping(value = "/saveRol")
    public void saveRol(@RequestBody
    RolDTO rolDTO) throws Exception {
        try {
            Rol rol = rolMapper.rolDTOToRol(rolDTO);

            businessDelegatorView.saveRol(rol);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteRol/{codeRol}")
    public void deleteRol(@PathVariable("codeRol")
    String codeRol) throws Exception {
        try {
            Rol rol = businessDelegatorView.getRol(codeRol);

            businessDelegatorView.deleteRol(rol);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateRol/")
    public void updateRol(@RequestBody
    RolDTO rolDTO) throws Exception {
        try {
            Rol rol = rolMapper.rolDTOToRol(rolDTO);

            businessDelegatorView.updateRol(rol);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataRol")
    public List<RolDTO> getDataRol() throws Exception {
        try {
            return businessDelegatorView.getDataRol();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getRol/{codeRol}")
    public RolDTO getRol(@PathVariable("codeRol")
    String codeRol) throws Exception {
        try {
            Rol rol = businessDelegatorView.getRol(codeRol);

            return rolMapper.rolToRolDTO(rol);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
