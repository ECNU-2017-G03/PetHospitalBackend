package com.ecnu.g03.pethospital.dto.admin.response.disease;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:07
 */
@NoArgsConstructor
@Data
public class DiseaseDeleteResponse extends BaseResponse {

    private boolean isSuccessful;

}
