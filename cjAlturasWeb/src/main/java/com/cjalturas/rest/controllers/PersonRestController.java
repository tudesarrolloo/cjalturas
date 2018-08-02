package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IPersonMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.PersonDTO;

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
@RequestMapping("/person")
public class PersonRestController {
    private static final Logger log = LoggerFactory.getLogger(PersonRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IPersonMapper personMapper;

    @PostMapping(value = "/savePerson")
    public void savePerson(@RequestBody
    PersonDTO personDTO) throws Exception {
        try {
            Person person = personMapper.personDTOToPerson(personDTO);

            businessDelegatorView.savePerson(person);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deletePerson/{idPerson}")
    public void deletePerson(@PathVariable("idPerson")
    Integer idPerson) throws Exception {
        try {
            Person person = businessDelegatorView.getPerson(idPerson);

            businessDelegatorView.deletePerson(person);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updatePerson/")
    public void updatePerson(@RequestBody
    PersonDTO personDTO) throws Exception {
        try {
            Person person = personMapper.personDTOToPerson(personDTO);

            businessDelegatorView.updatePerson(person);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataPerson")
    public List<PersonDTO> getDataPerson() throws Exception {
        try {
            return businessDelegatorView.getDataPerson();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getPerson/{idPerson}")
    public PersonDTO getPerson(@PathVariable("idPerson")
    Integer idPerson) throws Exception {
        try {
            Person person = businessDelegatorView.getPerson(idPerson);

            return personMapper.personToPersonDTO(person);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
