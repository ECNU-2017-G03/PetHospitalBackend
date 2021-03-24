package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.request.UserRequest;
import com.ecnu.g03.pethospital.dto.response.TestResponse;
import com.ecnu.g03.pethospital.service.UserService;
import com.ecnu.g03.pethospital.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (status) {
            String jwt = userService.createJwt(userRequest.getName());
            response.setMessage(jwt);
        } else {
            response.setMessage("hello world!");
        }

        return response;
    }

    @GetMapping("/user/help")
    public void help() {
        String jwt = userService.createJwt("momo");
        userService.validateJwt(jwt);
    }

    @GetMapping("/user/test")
    @JwtToken
    public void test(@RequestBody String jwt) {
        System.out.println("hello");
    }
}
