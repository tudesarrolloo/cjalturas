package com.cjalturas.rest.controllers;

import java.util.List;

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

import com.cjalturas.dto.mapper.IGroupMapper;
import com.cjalturas.model.Group;
import com.cjalturas.model.dto.GroupDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;


@RestController
@RequestMapping("/group")
public class GroupRestController {
  private static final Logger log = LoggerFactory.getLogger(GroupRestController.class);

  @Autowired
  private IBusinessDelegatorView businessDelegatorView;

  @Autowired
  private IGroupMapper groupMapper;

  @PostMapping(value = "/saveGroup")
  public void saveGroup(@RequestBody GroupDTO groupDTO) throws Exception {
    try {
      Group group = groupMapper.groupDTOToGroup(groupDTO);

      businessDelegatorView.saveGroup(group);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @DeleteMapping(value = "/deleteGroup/{idGroup}")
  public void deleteGroup(@PathVariable("idGroup") Integer idGroup) throws Exception {
    try {
      Group group = businessDelegatorView.getGroup(idGroup);

      businessDelegatorView.deleteGroup(group);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @PutMapping(value = "/updateGroup/")
  public void updateGroup(@RequestBody GroupDTO groupDTO) throws Exception {
    try {
      Group group = groupMapper.groupDTOToGroup(groupDTO);

      businessDelegatorView.updateGroup(group);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @GetMapping(value = "/getDataGroup")
  public List<GroupDTO> getDataGroup() throws Exception {
    try {
      return businessDelegatorView.getDataGroup();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @GetMapping(value = "/getGroup/{idGroup}")
  public GroupDTO getGroup(@PathVariable("idGroup") Integer idGroup) throws Exception {
    try {
      Group group = businessDelegatorView.getGroup(idGroup);

      return groupMapper.groupToGroupDTO(group);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }
}
