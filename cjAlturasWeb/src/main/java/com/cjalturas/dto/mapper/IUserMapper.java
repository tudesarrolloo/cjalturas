package com.cjalturas.dto.mapper;

import com.cjalturas.model.User;
import com.cjalturas.model.dto.UserDTO;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IUserMapper {

  public UserDTO userToUserDTO(User user) throws Exception;

}
