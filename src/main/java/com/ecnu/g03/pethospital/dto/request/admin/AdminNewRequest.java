package com.ecnu.g03.pethospital.dto.request.admin;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/3/29 15:01
 */
@Data
public class AdminNewRequest {

    /**
     * the admin who send this request
     */
    private String operator;

    /**
     * the username of new admin
     */
    private String name;

    /**
     * the password of new admin
     */
    private String password;

}
