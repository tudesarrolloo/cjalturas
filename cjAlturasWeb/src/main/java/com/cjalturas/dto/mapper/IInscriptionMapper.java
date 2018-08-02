package com.cjalturas.dto.mapper;

import com.cjalturas.model.Inscription;
import com.cjalturas.model.dto.InscriptionDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface IInscriptionMapper {
    public InscriptionDTO inscriptionToInscriptionDTO(Inscription inscription)
        throws Exception;

    public Inscription inscriptionDTOToInscription(
        InscriptionDTO inscriptionDTO) throws Exception;

    public List<InscriptionDTO> listInscriptionToListInscriptionDTO(
        List<Inscription> inscriptions) throws Exception;

    public List<Inscription> listInscriptionDTOToListInscription(
        List<InscriptionDTO> inscriptionDTOs) throws Exception;
}
