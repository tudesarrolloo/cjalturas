package com.cjalturas.dto.mapper;

import com.cjalturas.model.Enterprise;
import com.cjalturas.model.dto.EnterpriseDTO;

import java.util.List;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IEnterpriseMapper {
  public EnterpriseDTO enterpriseToEnterpriseDTO(Enterprise enterprise) throws Exception;

  public Enterprise enterpriseDTOToEnterprise(EnterpriseDTO enterpriseDTO) throws Exception;

  public List<EnterpriseDTO> listEnterpriseToListEnterpriseDTO(List<Enterprise> enterprises) throws Exception;

  public List<Enterprise> listEnterpriseDTOToListEnterprise(List<EnterpriseDTO> enterpriseDTOs) throws Exception;
}
