package com.ecnu.g03.pethospital.dto.admin.response.disease;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:07
 */
@Data
@NoArgsConstructor
public class DiseaseSearchResponse extends BaseResponse {

    private List<DiseaseEntity> diseases;

}
