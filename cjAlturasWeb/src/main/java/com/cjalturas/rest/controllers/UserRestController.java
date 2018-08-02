package com.cjalturas.rest.controllers;

import com.cjalturas.dto.mapper.IUserMapper;

import com.cjalturas.model.*;
import com.cjalturas.model.dto.UserDTO;

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
@RequestMapping("/user")
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);
    @Autowired
    private IBusinessDelegatorView businessDelegatorView;
    @Autowired
    private IUserMapper userMapper;

    @PostMapping(value = "/saveUser")
    public void saveUser(@RequestBody
    UserDTO userDTO) throws Exception {
        try {
            User user = userMapper.userDTOToUser(userDTO);

            businessDelegatorView.saveUser(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteUser/{idUser}")
    public void deleteUser(@PathVariable("idUser")
    Integer idUser) throws Exception {
        try {
            User user = businessDelegatorView.getUser(idUser);

            businessDelegatorView.deleteUser(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateUser/")
    public void updateUser(@RequestBody
    UserDTO userDTO) throws Exception {
        try {
            User user = userMapper.userDTOToUser(userDTO);

            businessDelegatorView.updateUser(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataUser")
    public List<UserDTO> getDataUser() throws Exception {
        try {
            return businessDelegatorView.getDataUser();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getUser/{idUser}")
    public UserDTO getUser(@PathVariable("idUser")
    Integer idUser) throws Exception {
        try {
            User user = businessDelegatorView.getUser(idUser);

            return userMapper.userToUserDTO(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
