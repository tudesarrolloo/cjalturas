package com.cjalturas.dto.mapper;

import com.cjalturas.model.Permit;
import com.cjalturas.model.dto.PermitDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IPermitMapper {
    public PermitDTO permitToPermitDTO(Permit permit) throws Exception;

    public Permit permitDTOToPermit(PermitDTO permitDTO)
        throws Exception;

    public List<PermitDTO> listPermitToListPermitDTO(List<Permit> permits)
        throws Exception;

    public List<Permit> listPermitDTOToListPermit(List<PermitDTO> permitDTOs)
        throws Exception;
}
