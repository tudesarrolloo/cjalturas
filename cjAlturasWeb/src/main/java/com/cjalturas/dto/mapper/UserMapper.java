package com.cjalturas.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cjalturas.model.User;
import com.cjalturas.model.control.IPersonLogic;
import com.cjalturas.model.control.IRolLogic;
import com.cjalturas.model.dto.UserDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Component
@Scope("singleton")
public class UserMapper implements IUserMapper {

  /**
   * Logic injected by Spring that manages Person entities
   */
  @Autowired
  IPersonLogic logicPerson1;

  /**
   * Logic injected by Spring that manages Rol entities
   */
  @Autowired
  IRolLogic logicRol2;

  @Transactional(readOnly = true)
  public UserDTO userToUserDTO(User user) throws Exception {
    try {
      UserDTO userDto = new UserDTO();
      userDto.setIdUser(user.getIdUser());
      userDto.setUsername(MapperUtil.getValue(user.getUsername()));
      userDto.setPassword(MapperUtil.getValue(user.getPassword()));
      userDto.setEnabled(MapperUtil.getBooleanValueFromString(user.getEnabled()));
      userDto.setPerson(user.getPerson());
      userDto.setRol(user.getRol());
      return userDto;
    } catch (Exception e) {
      throw e;
    }
  }

}
