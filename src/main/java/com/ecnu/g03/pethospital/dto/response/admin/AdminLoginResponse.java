package com.ecnu.g03.pethospital.dto.response.admin;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.dto.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 22:42
 */
@NoArgsConstructor
@Data
public class AdminLoginResponse extends BaseResponse {

    private Boolean isSuccessful;
    private AdminRole role;

}
