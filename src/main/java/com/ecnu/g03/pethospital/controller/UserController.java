package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.request.UserRequest;
import com.ecnu.g03.pethospital.dto.response.LoginResponse;
import com.ecnu.g03.pethospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * @date 2021-03-22 10:27
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        String token = userService.loginValidation(userRequest.getName(), userRequest.getPassword());

        if (token == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
        }
    }
}
