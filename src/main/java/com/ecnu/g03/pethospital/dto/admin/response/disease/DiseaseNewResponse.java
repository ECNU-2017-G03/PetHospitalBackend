package com.ecnu.g03.pethospital.dto.admin.response.disease;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:06
 */
@NoArgsConstructor
@Data
public class DiseaseNewResponse extends BaseResponse {

    private DiseaseEntity disease;

}
