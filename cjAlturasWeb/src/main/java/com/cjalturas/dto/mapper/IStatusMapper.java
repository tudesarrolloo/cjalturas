package com.cjalturas.dto.mapper;

import com.cjalturas.model.Status;
import com.cjalturas.model.dto.StatusDTO;

import java.util.List;


/**
 * @author Zathura Code Generator http://zathuracode.org www.zathuracode.org
 *
 */
public interface IStatusMapper {
  public StatusDTO statusToStatusDTO(Status status) throws Exception;

  public Status statusDTOToStatus(StatusDTO statusDTO) throws Exception;

  public List<StatusDTO> listStatusToListStatusDTO(List<Status> statuss) throws Exception;

  public List<Status> listStatusDTOToListStatus(List<StatusDTO> statusDTOs) throws Exception;
}
