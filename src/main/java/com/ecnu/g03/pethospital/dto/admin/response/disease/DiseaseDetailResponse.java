package com.ecnu.g03.pethospital.dto.admin.response.disease;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/22 22:28
 */
@Data
public class DiseaseDetailResponse extends BaseResponse {

    private DiseaseEntity disease;

}
