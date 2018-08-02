package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Rol;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.RolDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Component
@Scope("singleton")
public class RolMapper implements IRolMapper {
    private static final Logger log = LoggerFactory.getLogger(RolMapper.class);

    @Transactional(readOnly = true)
    public RolDTO rolToRolDTO(Rol rol) throws Exception {
        try {
            RolDTO rolDTO = new RolDTO();

            rolDTO.setCodeRol(rol.getCodeRol());
            rolDTO.setRol((rol.getRol() != null) ? rol.getRol() : null);

            return rolDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Rol rolDTOToRol(RolDTO rolDTO) throws Exception {
        try {
            Rol rol = new Rol();

            rol.setCodeRol(rolDTO.getCodeRol());
            rol.setRol((rolDTO.getRol() != null) ? rolDTO.getRol() : null);

            return rol;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<RolDTO> listRolToListRolDTO(List<Rol> listRol)
        throws Exception {
        try {
            List<RolDTO> rolDTOs = new ArrayList<RolDTO>();

            for (Rol rol : listRol) {
                RolDTO rolDTO = rolToRolDTO(rol);

                rolDTOs.add(rolDTO);
            }

            return rolDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Rol> listRolDTOToListRol(List<RolDTO> listRolDTO)
        throws Exception {
        try {
            List<Rol> listRol = new ArrayList<Rol>();

            for (RolDTO rolDTO : listRolDTO) {
                Rol rol = rolDTOToRol(rolDTO);

                listRol.add(rol);
            }

            return listRol;
        } catch (Exception e) {
            throw e;
        }
    }
}
