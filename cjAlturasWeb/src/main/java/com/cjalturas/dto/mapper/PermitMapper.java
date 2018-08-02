package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Permit;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.PermitDTO;

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
public class PermitMapper implements IPermitMapper {
    private static final Logger log = LoggerFactory.getLogger(PermitMapper.class);

    /**
    * Logic injected by Spring that manages Rol entities
    *
    */
    @Autowired
    IRolLogic logicRol1;

    @Transactional(readOnly = true)
    public PermitDTO permitToPermitDTO(Permit permit) throws Exception {
        try {
            PermitDTO permitDTO = new PermitDTO();

            permitDTO.setIdPermit(permit.getIdPermit());
            permitDTO.setResource((permit.getResource() != null)
                ? permit.getResource() : null);
            permitDTO.setCodeRol_Rol((permit.getRol().getCodeRol() != null)
                ? permit.getRol().getCodeRol() : null);

            return permitDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Permit permitDTOToPermit(PermitDTO permitDTO)
        throws Exception {
        try {
            Permit permit = new Permit();

            permit.setIdPermit(permitDTO.getIdPermit());
            permit.setResource((permitDTO.getResource() != null)
                ? permitDTO.getResource() : null);

            Rol rol = new Rol();

            if (permitDTO.getCodeRol_Rol() != null) {
                rol = logicRol1.getRol(permitDTO.getCodeRol_Rol());
            }

            if (rol != null) {
                permit.setRol(rol);
            }

            return permit;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<PermitDTO> listPermitToListPermitDTO(List<Permit> listPermit)
        throws Exception {
        try {
            List<PermitDTO> permitDTOs = new ArrayList<PermitDTO>();

            for (Permit permit : listPermit) {
                PermitDTO permitDTO = permitToPermitDTO(permit);

                permitDTOs.add(permitDTO);
            }

            return permitDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Permit> listPermitDTOToListPermit(List<PermitDTO> listPermitDTO)
        throws Exception {
        try {
            List<Permit> listPermit = new ArrayList<Permit>();

            for (PermitDTO permitDTO : listPermitDTO) {
                Permit permit = permitDTOToPermit(permitDTO);

                listPermit.add(permit);
            }

            return listPermit;
        } catch (Exception e) {
            throw e;
        }
    }
}
