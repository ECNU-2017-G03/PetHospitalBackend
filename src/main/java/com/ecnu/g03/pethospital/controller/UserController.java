package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.BaseTableDao;
import com.ecnu.g03.pethospital.dao.UserTableDao;
import com.ecnu.g03.pethospital.dto.request.UserRequest;
import com.ecnu.g03.pethospital.dto.response.TestResponse;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.service.UserService;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiayi Zhu
 * @date 2021-03-22 10:27
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    @ResponseBody
    public TestResponse login(@RequestBody UserRequest userRequest) {
        boolean status = userService.loginValidation(userRequest.getName(), userRequest.getPassword());

        TestResponse response = new TestResponse();
        response.setMessage("hello world!");
        return response;
    }
}
