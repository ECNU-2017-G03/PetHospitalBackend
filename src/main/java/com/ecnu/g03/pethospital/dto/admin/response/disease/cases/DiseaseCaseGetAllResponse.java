package com.ecnu.g03.pethospital.dto.admin.response.disease.cases;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:41
 */
@Data
@NoArgsConstructor
public class DiseaseCaseGetAllResponse extends BaseResponse {

    private List<DiseaseCaseEntity> diseaseCases;

}
