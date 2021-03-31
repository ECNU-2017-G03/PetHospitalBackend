package com.ecnu.g03.pethospital.dto.admin.response.admin;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/3/31 14:57
 */
@Data
public class AdminUpdateResponse extends BaseResponse {

    private AdminEntity admin;

}
