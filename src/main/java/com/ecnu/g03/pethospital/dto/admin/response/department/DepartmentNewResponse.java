package com.ecnu.g03.pethospital.dto.admin.response.department;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/3/30 23:30
 */
@Data
@NoArgsConstructor
public class DepartmentNewResponse extends BaseResponse {

    DepartmentEntity department;

}
