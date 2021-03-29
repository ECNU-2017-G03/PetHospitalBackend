package com.ecnu.g03.pethospital.dto.response.learning;

import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/25 11:36
 */
@Data
@AllArgsConstructor
public class SingleDiseaseCaseResponse {
    private DiseaseCaseEntity diseaseCaseEntity;
}