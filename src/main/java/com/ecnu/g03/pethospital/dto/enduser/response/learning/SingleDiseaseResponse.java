package com.ecnu.g03.pethospital.dto.enduser.response.learning;

import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/24 14:34
 */
@Data
@AllArgsConstructor
public class SingleDiseaseResponse {
    private DiseaseEntity diseaseEntity;
}