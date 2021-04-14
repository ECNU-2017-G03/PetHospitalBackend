package com.ecnu.g03.pethospital.dto.admin.response.disease.cases;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:06
 */
@Data
@NoArgsConstructor
public class DiseaseCaseSearchResponse extends BaseResponse {

    private List<DiseaseCaseEntity> diseaseCases;

}
