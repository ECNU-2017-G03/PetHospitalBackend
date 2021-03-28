package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.request.user.UserPasswordRequest;
import com.ecnu.g03.pethospital.dto.request.user.UserRequest;
import com.ecnu.g03.pethospital.dto.response.user.LoginResponse;
import com.ecnu.g03.pethospital.dto.response.user.UserActorResponse;
import com.ecnu.g03.pethospital.interceptor.JwtInterceptor;
import com.ecnu.g03.pethospital.model.status.UserRegisterStatus;
import com.ecnu.g03.pethospital.service.UserService;
import com.ecnu.g03.pethospital.util.JwtUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Juntao Peng, Jiayi Zhu
 * @date Created in 2021/3/25 21:00
 */
@WebMvcTest(UserController.class)
class UserControllerUT {
    private final UserRequest userRequest = new UserRequest("name", "password");
    private final String token = "ouldBeLongEnough";
    private final String id = "id";
    private final UserPasswordRequest userPasswordRequest = new UserPasswordRequest("old", "new");
    private final String[] actors = new String[]{"a1", "a2"};
    private final String AUTH_HEADER_KEY = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private final Gson gson = new Gson();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtInterceptor jwtInterceptor;

    @BeforeEach
    public void init() throws Exception {
        when(jwtInterceptor.preHandle(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(true);
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testLoginRequestOK() throws Exception {
        String name = userRequest.getName(), password = userRequest.getPassword();
        String requestJson = gson.toJson(userRequest);
        LoginResponse loginResponse = new LoginResponse(token);
        String responseJson = gson.toJson(loginResponse);

        when(userService.loginValidation(name, password)).thenReturn(token);

        mockMvc.perform(
                post("/user/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

    }

    @Test
    public void testLoginRequestUnauthorized() throws Exception {
        String name = userRequest.getName(), password = userRequest.getPassword();
        String requestJson = gson.toJson(userRequest);

        when(userService.loginValidation(name, password)).thenReturn(null);

        mockMvc.perform(
                post("/user/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testRegisterRequestCreated() throws Exception {
        String name = userRequest.getName(), password = userRequest.getPassword();
        String requestJson = gson.toJson(userRequest);

        when(userService.addUser(name, password)).thenReturn(UserRegisterStatus.OK);

        mockMvc.perform(
                post("/user/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterRequestBadRequest() throws Exception {
        String name = userRequest.getName(), password = userRequest.getPassword();
        String requestJson = gson.toJson(userRequest);

        when(userService.addUser(name, password)).thenReturn(UserRegisterStatus.ERROR);

        mockMvc.perform(
                post("/user/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterRequestConflict() throws Exception {
        String name = userRequest.getName(), password = userRequest.getPassword();
        String requestJson = gson.toJson(userRequest);

        when(userService.addUser(name, password)).thenReturn(UserRegisterStatus.EXIST);

        mockMvc.perform(
                post("/user/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isConflict());
    }

    @Test
    public void testChangePasswordRequestNoContent() throws Exception {
        try (MockedStatic<JwtUtil> dummyJwtUtil = Mockito.mockStatic(JwtUtil.class)) {
            String oldPassword = userPasswordRequest.getOldPassword();
            String newPassword = userPasswordRequest.getNewPassword();
            String requestJson = gson.toJson(userPasswordRequest);

            dummyJwtUtil.when(() -> JwtUtil.getUserId(token)).thenReturn(id);
            when(userService.changeUserPassword(oldPassword, newPassword, id)).thenReturn(true);

            mockMvc.perform(
                    put("/user/user/changePassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .header(AUTH_HEADER_KEY, TOKEN_PREFIX + token))
                    .andExpect(status().isNoContent());
        }
    }

    @Test
    public void testChangePasswordRequestBadRequest() throws Exception {
        try (MockedStatic<JwtUtil> dummyJwtUtil = Mockito.mockStatic(JwtUtil.class)) {
            String oldPassword = userPasswordRequest.getOldPassword();
            String newPassword = userPasswordRequest.getNewPassword();
            String requestJson = gson.toJson(userPasswordRequest);

            dummyJwtUtil.when(() -> JwtUtil.getUserId(token)).thenReturn(id);
            when(userService.changeUserPassword(oldPassword, newPassword, id)).thenReturn(false);

            mockMvc.perform(
                    put("/user/user/changePassword")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson)
                            .header(AUTH_HEADER_KEY, TOKEN_PREFIX + token))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    public void testGetActorsRequestOK() throws Exception {
        try (MockedStatic<JwtUtil> dummyJwtUtil = Mockito.mockStatic(JwtUtil.class)) {
            List<String> actorList = Arrays.asList(actors);

            dummyJwtUtil.when(() -> JwtUtil.getUserId(token)).thenReturn(id);
            when(userService.getUserActors(id)).thenReturn(actorList);


            mockMvc.perform(
                    get("/user/user/actors")
                            .header(AUTH_HEADER_KEY, TOKEN_PREFIX + token))
                    .andExpect(status().isOk())
                    .andExpect(content().json(gson.toJson(new UserActorResponse(actorList))));
        }
    }
}