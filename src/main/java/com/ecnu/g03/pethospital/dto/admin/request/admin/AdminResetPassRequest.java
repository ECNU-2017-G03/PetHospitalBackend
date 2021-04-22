package com.ecnu.g03.pethospital.dto.admin.request.admin;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/21 14:23
 */
@Data
public class AdminResetPassRequest {

    String userName;
    String oldePassword;
    String newPassword;

}
