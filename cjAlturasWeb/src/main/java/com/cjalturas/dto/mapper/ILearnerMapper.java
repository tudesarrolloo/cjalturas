package com.cjalturas.dto.mapper;

import com.cjalturas.model.Learner;
import com.cjalturas.model.dto.LearnerDTO;

import java.util.List;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public interface ILearnerMapper {
    public LearnerDTO learnerToLearnerDTO(Learner learner)
        throws Exception;

    public Learner learnerDTOToLearner(LearnerDTO learnerDTO)
        throws Exception;

    public List<LearnerDTO> listLearnerToListLearnerDTO(List<Learner> learners)
        throws Exception;

    public List<Learner> listLearnerDTOToListLearner(
        List<LearnerDTO> learnerDTOs) throws Exception;
}
