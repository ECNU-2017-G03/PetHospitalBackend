package com.ecnu.g03.pethospital.dto.enduser.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:29
 */
@Data
@AllArgsConstructor
public class UserActorResponse {
    List<String> actors;
}
