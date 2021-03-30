package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.enduser.response.department.DepartmentOverviewResponse;
import com.ecnu.g03.pethospital.interceptor.JwtInterceptor;
import com.ecnu.g03.pethospital.service.DepartmentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Juntao Peng, Jiayi Zhu
 * @date 2021/3/25 10:00
 */
@WebMvcTest(DepartmentController.class)
class DepartmentControllerUT {
    private final String[] departmentArray = new String[]{"departmentA", "departmentB"};
    private final String token = "ouldBeLongEnough";
    private final String AUTH_HEADER_KEY = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private final Gson gson = new Gson();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
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
    @Disabled
    // todo: fix
    public void testGetDepartmentsRequestOK() throws Exception {
        List<String> departmentList = Arrays.asList(departmentArray);

        when(departmentService.getDepartmentList()).thenReturn(departmentList);

        mockMvc.perform(get("/user/department/list"))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(new DepartmentOverviewResponse(departmentList))));
    }

    @Test
    @Disabled
    // todo: fix
    public void testGetDepartmentsRequestServiceUnavailable() throws Exception {
        when(departmentService.getDepartmentList()).thenReturn(null);

        mockMvc.perform(get("/user/department/list"))
                .andExpect(status().isServiceUnavailable());
    }

}