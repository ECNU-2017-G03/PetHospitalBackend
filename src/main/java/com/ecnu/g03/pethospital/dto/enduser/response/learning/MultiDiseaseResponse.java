package com.ecnu.g03.pethospital.dto.enduser.response.learning;

import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/25 11:33
 */
@Data
@AllArgsConstructor
public class MultiDiseaseResponse {
    private List<DiseaseEntity> diseaseEntityList;
}