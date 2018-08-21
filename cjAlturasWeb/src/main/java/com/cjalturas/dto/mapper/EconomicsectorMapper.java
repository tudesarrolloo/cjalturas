package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Economicsector;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.EconomicsectorDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
@Component
@Scope("singleton")
public class EconomicsectorMapper implements IEconomicsectorMapper {
  private static final Logger log = LoggerFactory.getLogger(EconomicsectorMapper.class);

  @Transactional(readOnly = true)
  public EconomicsectorDTO economicsectorToEconomicsectorDTO(Economicsector economicsector) throws Exception {
    try {
      EconomicsectorDTO economicsectorDTO = new EconomicsectorDTO();

      economicsectorDTO.setIdEconomicSector(economicsector.getIdEconomicSector());
      economicsectorDTO.setEconomicSector((economicsector.getEconomicSector() != null) ? economicsector.getEconomicSector() : null);

      return economicsectorDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Economicsector economicsectorDTOToEconomicsector(EconomicsectorDTO economicsectorDTO) throws Exception {
    try {
      Economicsector economicsector = new Economicsector();

      economicsector.setIdEconomicSector(economicsectorDTO.getIdEconomicSector());
      economicsector.setEconomicSector((economicsectorDTO.getEconomicSector() != null) ? economicsectorDTO.getEconomicSector() : null);

      return economicsector;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<EconomicsectorDTO> listEconomicsectorToListEconomicsectorDTO(List<Economicsector> listEconomicsector) throws Exception {
    try {
      List<EconomicsectorDTO> economicsectorDTOs = new ArrayList<EconomicsectorDTO>();

      for (Economicsector economicsector : listEconomicsector) {
        EconomicsectorDTO economicsectorDTO = economicsectorToEconomicsectorDTO(economicsector);

        economicsectorDTOs.add(economicsectorDTO);
      }

      return economicsectorDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Economicsector> listEconomicsectorDTOToListEconomicsector(List<EconomicsectorDTO> listEconomicsectorDTO) throws Exception {
    try {
      List<Economicsector> listEconomicsector = new ArrayList<Economicsector>();

      for (EconomicsectorDTO economicsectorDTO : listEconomicsectorDTO) {
        Economicsector economicsector = economicsectorDTOToEconomicsector(economicsectorDTO);

        listEconomicsector.add(economicsector);
      }

      return listEconomicsector;
    } catch (Exception e) {
      throw e;
    }
  }
}
