package com.ecnu.g03.pethospital.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 1:16
 */
@Data
@ConfigurationProperties(prefix = "audience")
@Component
@AllArgsConstructor
public class Audience {
    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;
}
