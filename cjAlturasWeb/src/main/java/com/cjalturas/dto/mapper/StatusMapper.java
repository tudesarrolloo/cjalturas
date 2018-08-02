package com.cjalturas.dto.mapper;

import com.cjalturas.model.*;
import com.cjalturas.model.Status;
import com.cjalturas.model.control.*;
import com.cjalturas.model.dto.StatusDTO;

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
public class StatusMapper implements IStatusMapper {
    private static final Logger log = LoggerFactory.getLogger(StatusMapper.class);

    @Transactional(readOnly = true)
    public StatusDTO statusToStatusDTO(Status status) throws Exception {
        try {
            StatusDTO statusDTO = new StatusDTO();

            statusDTO.setCode(status.getCode());
            statusDTO.setStatus((status.getStatus() != null)
                ? status.getStatus() : null);

            return statusDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Status statusDTOToStatus(StatusDTO statusDTO)
        throws Exception {
        try {
            Status status = new Status();

            status.setCode(statusDTO.getCode());
            status.setStatus((statusDTO.getStatus() != null)
                ? statusDTO.getStatus() : null);

            return status;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<StatusDTO> listStatusToListStatusDTO(List<Status> listStatus)
        throws Exception {
        try {
            List<StatusDTO> statusDTOs = new ArrayList<StatusDTO>();

            for (Status status : listStatus) {
                StatusDTO statusDTO = statusToStatusDTO(status);

                statusDTOs.add(statusDTO);
            }

            return statusDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Status> listStatusDTOToListStatus(List<StatusDTO> listStatusDTO)
        throws Exception {
        try {
            List<Status> listStatus = new ArrayList<Status>();

            for (StatusDTO statusDTO : listStatusDTO) {
                Status status = statusDTOToStatus(statusDTO);

                listStatus.add(status);
            }

            return listStatus;
        } catch (Exception e) {
            throw e;
        }
    }
}
