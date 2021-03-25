package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.request.user.UserPasswordRequest;
import com.ecnu.g03.pethospital.dto.request.user.UserRequest;
import com.ecnu.g03.pethospital.dto.response.user.LoginResponse;
import com.ecnu.g03.pethospital.dto.response.user.UserActorResponse;
import com.ecnu.g03.pethospital.model.entity.Audience;
import com.ecnu.g03.pethospital.model.status.UserRegisterStatus;
import com.ecnu.g03.pethospital.service.UserService;
import com.ecnu.g03.pethospital.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerUT {
    private final UserService userService = mock(UserService.class);
    private final UserRequest userRequest = new UserRequest("name", "password");
    private final String auth = "tokenShouldBeLongEnough";
    private final String token = "ouldBeLongEnough";
    private final String id = token;
    private final UserPasswordRequest userPasswordRequest = new UserPasswordRequest("old", "new");
    private final Audience audience = new Audience("c", "asdf", "n", 100);
    private final String[] actors = new String[] {"a1", "a2"};
    @BeforeEach
    public void init() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testLoginRequestOK() {
        String name = userRequest.getName(), password = userRequest.getPassword();
        when(userService.loginValidation(name, password)).thenReturn(token);

        UserController userController = new UserController(userService);
        ResponseEntity<?> response = userController.login(userRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                token,
                ((LoginResponse) Objects.requireNonNull(response.getBody())).getToken()
        );
    }

    @Test
    public void testLoginRequestUnauthorized() {
        String name = userRequest.getName(), password = userRequest.getPassword();
        when(userService.loginValidation(name, password)).thenReturn(null);

        UserController userController = new UserController(userService);
        ResponseEntity<?> response = userController.login(userRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testRegisterRequestCreated() {
        String name = userRequest.getName(), password = userRequest.getPassword();
        when(userService.addUser(name, password)).thenReturn(UserRegisterStatus.OK);

        UserController userController = new UserController(userService);
        ResponseEntity<?> response = userController.register(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testRegisterRequestBadRequest() {
        String name = userRequest.getName(), password = userRequest.getPassword();
        when(userService.addUser(name, password)).thenReturn(UserRegisterStatus.ERROR);

        UserController userController = new UserController(userService);
        ResponseEntity<?> response = userController.register(userRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testRegisterRequestConflict() {
        String name = userRequest.getName(), password = userRequest.getPassword();
        when(userService.addUser(name, password)).thenReturn(UserRegisterStatus.EXIST);

        UserController userController = new UserController(userService);
        ResponseEntity<?> response = userController.register(userRequest);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testChangePasswordRequestNoContent() {
        try (MockedStatic<JwtUtil> dummyJwtUtil = Mockito.mockStatic(JwtUtil.class)){
            dummyJwtUtil.when(() -> JwtUtil.getUserId(token))
                    .thenReturn(id);
            String oldPassword = userPasswordRequest.getOldPassword(), newPassword = userPasswordRequest.getNewPassword();
            when(userService.changeUserPassword(oldPassword, newPassword, id)).thenReturn(true);

            UserController userController = new UserController(userService);
            ResponseEntity<?> response = userController.changePassword(userPasswordRequest, auth);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }
    }

    @Test
    public void testChangePasswordRequestBadRequest() {
        try (MockedStatic<JwtUtil> dummyJwtUtil = Mockito.mockStatic(JwtUtil.class)){
            dummyJwtUtil.when(() -> JwtUtil.getUserId(token))
                    .thenReturn(id);
            String oldPassword = userPasswordRequest.getOldPassword(), newPassword = userPasswordRequest.getNewPassword();
            when(userService.changeUserPassword(oldPassword, newPassword, id)).thenReturn(false);

            UserController userController = new UserController(userService);
            ResponseEntity<?> response = userController.changePassword(userPasswordRequest, auth);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }
    }

    @Test
    public void testGetActorsRequestOK() {
        try (MockedStatic<JwtUtil> dummyJwtUtil = Mockito.mockStatic(JwtUtil.class)){
            dummyJwtUtil.when(() -> JwtUtil.getUserId(token))
                    .thenReturn(id);
            when(userService.getUserActors(id)).thenReturn(Arrays.asList(actors));

            UserController userController = new UserController(userService);
            ResponseEntity<?> response = userController.getActors(auth);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertArrayEquals(
                    actors,
                    ((UserActorResponse) Objects.requireNonNull(response.getBody())).getActors().toArray()
            );
        }
    }
}