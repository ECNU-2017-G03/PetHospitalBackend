package com.ecnu.g03.pethospital.dto.enduser.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 21:40
 */
@Data
@AllArgsConstructor
public class UserPasswordRequest {
    String oldPassword;
    String newPassword;
}
