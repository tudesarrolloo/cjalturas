package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IStatusMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.StatusDTO;

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
@RequestMapping("/status")
public class StatusRestController {
    private static final Logger log = LoggerFactory.getLogger(StatusRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IStatusMapper statusMapper;

    @PostMapping(value = "/saveStatus")
    public void saveStatus(@RequestBody
    StatusDTO statusDTO) throws Exception {
        try {
            Status status = statusMapper.statusDTOToStatus(statusDTO);

            businessDelegatorView.saveStatus(status);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteStatus/{code}")
    public void deleteStatus(@PathVariable("code")
    String code) throws Exception {
        try {
            Status status = businessDelegatorView.getStatus(code);

            businessDelegatorView.deleteStatus(status);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateStatus/")
    public void updateStatus(@RequestBody
    StatusDTO statusDTO) throws Exception {
        try {
            Status status = statusMapper.statusDTOToStatus(statusDTO);

            businessDelegatorView.updateStatus(status);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataStatus")
    public List<StatusDTO> getDataStatus() throws Exception {
        try {
            return businessDelegatorView.getDataStatus();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getStatus/{code}")
    public StatusDTO getStatus(@PathVariable("code")
    String code) throws Exception {
        try {
            Status status = businessDelegatorView.getStatus(code);

            return statusMapper.statusToStatusDTO(status);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
