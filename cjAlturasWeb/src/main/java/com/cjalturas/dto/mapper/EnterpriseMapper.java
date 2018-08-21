package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Enterprise;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.EnterpriseDTO;

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
public class EnterpriseMapper implements IEnterpriseMapper {
  private static final Logger log = LoggerFactory.getLogger(EnterpriseMapper.class);

  @Transactional(readOnly = true)
  public EnterpriseDTO enterpriseToEnterpriseDTO(Enterprise enterprise) throws Exception {
    try {
      EnterpriseDTO enterpriseDTO = new EnterpriseDTO();

      enterpriseDTO.setIdEnterprise(enterprise.getIdEnterprise());
      enterpriseDTO.setContactName((enterprise.getContactName() != null) ? enterprise.getContactName() : null);
      enterpriseDTO.setName((enterprise.getName() != null) ? enterprise.getName() : null);
      enterpriseDTO.setNit((enterprise.getNit() != null) ? enterprise.getNit() : null);
      enterpriseDTO.setPhone((enterprise.getPhone() != null) ? enterprise.getPhone() : null);

      return enterpriseDTO;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public Enterprise enterpriseDTOToEnterprise(EnterpriseDTO enterpriseDTO) throws Exception {
    try {
      Enterprise enterprise = new Enterprise();

      enterprise.setIdEnterprise(enterpriseDTO.getIdEnterprise());
      enterprise.setContactName((enterpriseDTO.getContactName() != null) ? enterpriseDTO.getContactName() : null);
      enterprise.setName((enterpriseDTO.getName() != null) ? enterpriseDTO.getName() : null);
      enterprise.setNit((enterpriseDTO.getNit() != null) ? enterpriseDTO.getNit() : null);
      enterprise.setPhone((enterpriseDTO.getPhone() != null) ? enterpriseDTO.getPhone() : null);

      return enterprise;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<EnterpriseDTO> listEnterpriseToListEnterpriseDTO(List<Enterprise> listEnterprise) throws Exception {
    try {
      List<EnterpriseDTO> enterpriseDTOs = new ArrayList<EnterpriseDTO>();

      for (Enterprise enterprise : listEnterprise) {
        EnterpriseDTO enterpriseDTO = enterpriseToEnterpriseDTO(enterprise);

        enterpriseDTOs.add(enterpriseDTO);
      }

      return enterpriseDTOs;
    } catch (Exception e) {
      throw e;
    }
  }

  @Transactional(readOnly = true)
  public List<Enterprise> listEnterpriseDTOToListEnterprise(List<EnterpriseDTO> listEnterpriseDTO) throws Exception {
    try {
      List<Enterprise> listEnterprise = new ArrayList<Enterprise>();

      for (EnterpriseDTO enterpriseDTO : listEnterpriseDTO) {
        Enterprise enterprise = enterpriseDTOToEnterprise(enterpriseDTO);

        listEnterprise.add(enterprise);
      }

      return listEnterprise;
    } catch (Exception e) {
      throw e;
    }
  }
}
