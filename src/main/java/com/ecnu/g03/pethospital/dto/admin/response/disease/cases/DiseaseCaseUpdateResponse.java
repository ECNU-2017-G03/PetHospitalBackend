package com.ecnu.g03.pethospital.dto.admin.response.disease.cases;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:42
 */
@Data
@NoArgsConstructor
public class DiseaseCaseUpdateResponse extends BaseResponse {

    private DiseaseCaseEntity diseaseCase;

}
