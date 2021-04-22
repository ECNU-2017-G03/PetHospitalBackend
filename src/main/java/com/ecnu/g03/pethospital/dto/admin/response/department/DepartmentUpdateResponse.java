package com.ecnu.g03.pethospital.dto.admin.response.department;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/12 8:46
 */
@Data
public class DepartmentUpdateResponse extends BaseResponse {

    private DepartmentEntity department;

}
