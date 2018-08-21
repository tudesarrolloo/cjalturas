package com.cjalturas.dto.mapper;

import com.cjalturas.model.Person;
import com.cjalturas.model.dto.PersonDTO;

import java.util.List;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IPersonMapper {
  public PersonDTO personToPersonDTO(Person person) throws Exception;

  public Person personDTOToPerson(PersonDTO personDTO) throws Exception;

  public List<PersonDTO> listPersonToListPersonDTO(List<Person> persons) throws Exception;

  public List<Person> listPersonDTOToListPerson(List<PersonDTO> personDTOs) throws Exception;
}
