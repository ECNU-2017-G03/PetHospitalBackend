package com.ecnu.g03.pethospital.dto.response;

import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/24 14:34
 */
@Data
public class FunctionalityLearningResponse {
    private DiseaseEntity diseaseEntity;
    private DiseaseCaseEntity diseaseCaseEntity;
    private List<DiseaseEntity> diseaseEntities;
    private List<DiseaseCaseEntity> diseaseCaseEntities;
}