package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.User;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.UserDTO;

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
public class UserMapper implements IUserMapper {
  private static final Logger log = LoggerFactory.getLogger(UserMapper.class);

  /**
   * Logic injected by Spring that manages Person entities
   *
   */
  @Autowired
  IPersonLogic logicPerson1;

  /**
   * Logic injected by Spring that manages Rol entities
   *
   */
  @Autowired
  IRolLogic logicRol2;

  @Transactional(readOnly = true)
  public UserDTO userToUserDTO(User user) throws Exception {
    try {
      UserDTO userDTO = new UserDTO();

      userDTO.setIdUser(user.getIdUser());
      userDTO.setEnabled((user.getEnabled() != null) ? user.getEnabled() : null);
      userDTO.setPassword((user.getPassword() != null) ? user.getPassword() : null);
      userDTO.setUsername((user.getUsername() != null) ? user.getUsername() : null);
      userDTO.setIdPerson_Person((user.getPerson().getIdPerson() != null) ? user.getPerson().getIdPerson() : null);
      userDTO.setCodeRol_Rol((user.getRol().getCodeRol() != null) ? user.getRol().getCodeRol() : null);

      return userDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public User userDTOToUser(UserDTO userDTO) throws Exception {
    try {
      User user = new User();

      user.setIdUser(userDTO.getIdUser());
      user.setEnabled((userDTO.getEnabled() != null) ? userDTO.getEnabled() : null);
      user.setPassword((userDTO.getPassword() != null) ? userDTO.getPassword() : null);
      user.setUsername((userDTO.getUsername() != null) ? userDTO.getUsername() : null);

      Person person = new Person();

      if (userDTO.getIdPerson_Person() != null) {
        person = logicPerson1.getPerson(userDTO.getIdPerson_Person());
      }

      if (person != null) {
        user.setPerson(person);
      }

      Rol rol = new Rol();

      if (userDTO.getCodeRol_Rol() != null) {
        rol = logicRol2.getRol(userDTO.getCodeRol_Rol());
      }

      if (rol != null) {
        user.setRol(rol);
      }

      return user;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<UserDTO> listUserToListUserDTO(List<User> listUser) throws Exception {
    try {
      List<UserDTO> userDTOs = new ArrayList<UserDTO>();

      for (User user : listUser) {
        UserDTO userDTO = userToUserDTO(user);

        userDTOs.add(userDTO);
      }

      return userDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<User> listUserDTOToListUser(List<UserDTO> listUserDTO) throws Exception {
    try {
      List<User> listUser = new ArrayList<User>();

      for (UserDTO userDTO : listUserDTO) {
        User user = userDTOToUser(userDTO);

        listUser.add(user);
      }

      return listUser;
    } catch (Exception e) {
      throw e;
    }
  }
}
