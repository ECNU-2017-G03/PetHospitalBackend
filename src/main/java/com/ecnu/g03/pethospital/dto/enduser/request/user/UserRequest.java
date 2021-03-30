package com.ecnu.g03.pethospital.dto.enduser.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 0:24
 */
@Data
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String password;
}
