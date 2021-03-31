package com.ecnu.g03.pethospital.dto.admin.request.admin;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/3/31 14:46
 */
@Data
public class AdminLoginRequest {

    private String userName;
    private String userKey;

}
