package com.ecnu.g03.pethospital.dto.request;

import lombok.Data;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 21:40
 */
@Data
public class UserPasswordRequest {
    String oldPassword;
    String newPassword;
}
