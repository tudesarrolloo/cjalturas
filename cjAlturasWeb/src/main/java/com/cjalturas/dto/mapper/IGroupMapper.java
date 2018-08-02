package com.cjalturas.dto.mapper;

import com.cjalturas.model.Group;
import com.cjalturas.model.dto.GroupDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IGroupMapper {
    public GroupDTO groupToGroupDTO(Group group) throws Exception;

    public Group groupDTOToGroup(GroupDTO groupDTO) throws Exception;

    public List<GroupDTO> listGroupToListGroupDTO(List<Group> groups)
        throws Exception;

    public List<Group> listGroupDTOToListGroup(List<GroupDTO> groupDTOs)
        throws Exception;
}
