package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Person;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.PersonDTO;

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
public class PersonMapper implements IPersonMapper {
  private static final Logger log = LoggerFactory.getLogger(PersonMapper.class);

  @Transactional(readOnly = true)
  public PersonDTO personToPersonDTO(Person person) throws Exception {
    try {
      PersonDTO personDTO = new PersonDTO();

      personDTO.setIdPerson(person.getIdPerson());
      personDTO.setDocument((person.getDocument() != null) ? person.getDocument() : null);
      personDTO.setDocumentType((person.getDocumentType() != null) ? person.getDocumentType() : null);
      personDTO.setEmail((person.getEmail() != null) ? person.getEmail() : null);
      personDTO.setLastname((person.getLastname() != null) ? person.getLastname() : null);
      personDTO.setName((person.getName() != null) ? person.getName() : null);
      personDTO.setPhone((person.getPhone() != null) ? person.getPhone() : null);
      personDTO.setBirthDate((person.getBirthDate() != null) ? person.getBirthDate() : null);

      return personDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Person personDTOToPerson(PersonDTO personDTO) throws Exception {
    try {
      Person person = new Person();

      person.setIdPerson(personDTO.getIdPerson());
      person.setDocument((personDTO.getDocument() != null) ? personDTO.getDocument() : null);
      person.setDocumentType((personDTO.getDocumentType() != null) ? personDTO.getDocumentType() : null);
      person.setEmail((personDTO.getEmail() != null) ? personDTO.getEmail() : null);
      person.setLastname((personDTO.getLastname() != null) ? personDTO.getLastname() : null);
      person.setName((personDTO.getName() != null) ? personDTO.getName() : null);
      person.setPhone((personDTO.getPhone() != null) ? personDTO.getPhone() : null);
      person.setBirthDate((personDTO.getBirthDate() != null) ? personDTO.getBirthDate() : null);

      return person;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<PersonDTO> listPersonToListPersonDTO(List<Person> listPerson) throws Exception {
    try {
      List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();

      for (Person person : listPerson) {
        PersonDTO personDTO = personToPersonDTO(person);

        personDTOs.add(personDTO);
      }

      return personDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Person> listPersonDTOToListPerson(List<PersonDTO> listPersonDTO) throws Exception {
    try {
      List<Person> listPerson = new ArrayList<Person>();

      for (PersonDTO personDTO : listPersonDTO) {
        Person person = personDTOToPerson(personDTO);

        listPerson.add(person);
      }

      return listPerson;
    } catch (Exception e) {
      throw e;
    }
  }
}
