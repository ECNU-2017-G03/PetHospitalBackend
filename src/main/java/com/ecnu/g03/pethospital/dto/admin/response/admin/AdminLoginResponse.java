package com.ecnu.g03.pethospital.dto.admin.response.admin;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/3/28 22:42
 */
@NoArgsConstructor
@Data
public class AdminLoginResponse extends BaseResponse {

    private Boolean isSuccessful;
    private AdminEntity admin;

}
