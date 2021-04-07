package com.ecnu.g03.pethospital.dto.admin.response.disease;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:07
 */
@Data
@NoArgsConstructor
public class DiseaseGetAllResponse extends BaseResponse {

    private List<DiseaseEntity> diseases;

}
