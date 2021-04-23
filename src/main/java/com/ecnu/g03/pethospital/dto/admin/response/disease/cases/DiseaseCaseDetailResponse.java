package com.ecnu.g03.pethospital.dto.admin.response.disease.cases;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/23 23:48
 */
@Data
public class DiseaseCaseDetailResponse extends BaseResponse {

    private DiseaseCaseEntity diseaseCase;

}
