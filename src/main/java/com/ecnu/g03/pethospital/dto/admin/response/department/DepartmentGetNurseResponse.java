package com.ecnu.g03.pethospital.dto.admin.response.department;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.parse.DepartmentDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/31 13:34
 */
@Data
@NoArgsConstructor
public class DepartmentGetNurseResponse extends BaseResponse {

    private DepartmentDetail nurses;

}
