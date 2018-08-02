package com.cjalturas.dto.mapper;

import com.cjalturas.model.Economicsector;
import com.cjalturas.model.dto.EconomicsectorDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IEconomicsectorMapper {
    public EconomicsectorDTO economicsectorToEconomicsectorDTO(
        Economicsector economicsector) throws Exception;

    public Economicsector economicsectorDTOToEconomicsector(
        EconomicsectorDTO economicsectorDTO) throws Exception;

    public List<EconomicsectorDTO> listEconomicsectorToListEconomicsectorDTO(
        List<Economicsector> economicsectors) throws Exception;

    public List<Economicsector> listEconomicsectorDTOToListEconomicsector(
        List<EconomicsectorDTO> economicsectorDTOs) throws Exception;
}
