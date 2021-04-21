package com.ecnu.g03.pethospital.dto.admin.response.disease.cases;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:41
 */
@Data
@NoArgsConstructor
public class DiseaseCaseDeleteResponse extends BaseResponse {

    private boolean isSuccessful;

}
