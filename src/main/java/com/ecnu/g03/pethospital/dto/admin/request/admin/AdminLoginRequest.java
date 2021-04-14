package com.ecnu.g03.pethospital.dto.admin.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/3/31 14:46
 */
@Data
public class AdminLoginRequest {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_key")
    private String userKey;

}
