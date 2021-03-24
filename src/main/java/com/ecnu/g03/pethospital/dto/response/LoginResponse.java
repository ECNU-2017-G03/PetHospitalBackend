package com.ecnu.g03.pethospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 20:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    String token;
}
