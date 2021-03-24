package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.request.UserPasswordRequest;
import com.ecnu.g03.pethospital.dto.request.UserRequest;
import com.ecnu.g03.pethospital.dto.response.LoginResponse;
import com.ecnu.g03.pethospital.dto.response.UserActorResponse;
import com.ecnu.g03.pethospital.service.UserService;
import com.ecnu.g03.pethospital.util.JwtToken;
import com.ecnu.g03.pethospital.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-22 10:27
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @param userRequest user name & password
     * @return token
     */
    @PostMapping("/user/user/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        String token = userService.loginValidation(userRequest.getName(), userRequest.getPassword());

        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
        }
    }

    /**
     * @param userRequest user name & password
     * @return null
     */
    @PostMapping("/user/user/register")
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        int status = userService.addUser(userRequest.getName(), userRequest.getPassword());
        if (status == 0) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else if (status == 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (status == 2) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param userPasswordRequest old password & new password
     * @param auth token
     * @return null
     */
    @PutMapping("/user/user/changePassword")
    @JwtToken
    public ResponseEntity<?> changePassword(@RequestBody UserPasswordRequest userPasswordRequest,
                                            @RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        String id = JwtUtil.getUserId(token);
        String oldPassword = userPasswordRequest.getOldPassword();
        String newPassword = userPasswordRequest.getNewPassword();
        boolean status = userService.changeUserPassword(oldPassword, newPassword, id);
        if (status) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param auth token
     * @return actor list
     */
    @GetMapping("/user/user/actors")
    @JwtToken
    public ResponseEntity<?> getActors(@RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        String id = JwtUtil.getUserId(token);
        List<String> actors = userService.getUserActors(id);
        UserActorResponse response = new UserActorResponse(actors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
