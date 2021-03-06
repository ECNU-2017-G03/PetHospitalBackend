package com.ecnu.g03.pethospital.dto.enduser.response.learning;

import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/25 11:36
 */
@Data
@AllArgsConstructor
public class MultiDiseaseCaseResponse {
    private List<DiseaseCaseEntity> diseaseCaseEntityList;
}