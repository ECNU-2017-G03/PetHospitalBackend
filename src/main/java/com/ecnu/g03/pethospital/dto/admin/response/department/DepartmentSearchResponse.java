package com.ecnu.g03.pethospital.dto.admin.response.department;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:08
 */
@Data
@NoArgsConstructor
public class DepartmentSearchResponse extends BaseResponse {

    private List<DepartmentEntity> departments;

}
