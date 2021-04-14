package com.ecnu.g03.pethospital.dto.admin.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    private String operator;

    /**
     * the username of new admin
     */
    @JsonProperty("name")
    private String name;

    /**
     * the password of new admin
     */
    @JsonProperty("password")
    private String password;

}
