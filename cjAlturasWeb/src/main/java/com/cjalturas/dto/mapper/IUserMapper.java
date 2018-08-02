package com.cjalturas.dto.mapper;

import com.cjalturas.model.User;
import com.cjalturas.model.dto.UserDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IUserMapper {
    public UserDTO userToUserDTO(User user) throws Exception;

    public User userDTOToUser(UserDTO userDTO) throws Exception;

    public List<UserDTO> listUserToListUserDTO(List<User> users)
        throws Exception;

    public List<User> listUserDTOToListUser(List<UserDTO> userDTOs)
        throws Exception;
}
