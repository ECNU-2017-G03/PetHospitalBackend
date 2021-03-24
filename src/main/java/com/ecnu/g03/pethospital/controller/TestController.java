package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.TestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shen Lei
 * @Date 2021/3/17 14:19
 */
@RestController
@CrossOrigin
@PropertySource(value = "classpath:application.yml")
public class TestController {

    @Value("${azure.storage.account-name}")
    private String azurename;

    @GetMapping("/test")
    public TestResponse test() {
        TestResponse response = new TestResponse();
        response.setMessage("hello world!" + azurename);
        return response;
    }
}
