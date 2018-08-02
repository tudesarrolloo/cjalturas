package com.cjalturas.dto.mapper;

import com.cjalturas.model.Rol;
import com.cjalturas.model.dto.RolDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IRolMapper {
    public RolDTO rolToRolDTO(Rol rol) throws Exception;

    public Rol rolDTOToRol(RolDTO rolDTO) throws Exception;

    public List<RolDTO> listRolToListRolDTO(List<Rol> rols)
        throws Exception;

    public List<Rol> listRolDTOToListRol(List<RolDTO> rolDTOs)
        throws Exception;
}
