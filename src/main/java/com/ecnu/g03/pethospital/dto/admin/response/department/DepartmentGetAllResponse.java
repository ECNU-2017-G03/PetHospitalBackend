package com.ecnu.g03.pethospital.dto.admin.response.department;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/30 23:23
 */
@Data
@NoArgsConstructor
public class DepartmentGetAllResponse extends BaseResponse {

    List<DepartmentEntity> departments;

}
