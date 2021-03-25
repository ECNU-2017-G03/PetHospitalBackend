package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.department.DepartmentOverviewResponse;
import com.ecnu.g03.pethospital.service.DepartmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @author Juntao Peng
 * @date 2021/3/25 10:00
 */
class DepartmentControllerUT {
    private final DepartmentService departmentService = mock(DepartmentService.class);
    private final String[] departmentArray = new String[]{"departmentA", "departmentB"};

    @BeforeEach
    public void init() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testGetDepartmentsRequestOK() {
        when(departmentService.getDepartmentList())
                .thenReturn(Arrays.asList(departmentArray));

        DepartmentController departmentController =
                new DepartmentController(departmentService);
        ResponseEntity<?> response =
                departmentController.getDepartments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(
                departmentArray,
                ((DepartmentOverviewResponse) Objects.requireNonNull(response.getBody())).getDepartmentList().toArray()
        );
    }

    @Test
    public void testGetDepartmentsRequestServiceUnavailable() {
        when(departmentService.getDepartmentList())
                .thenReturn(null);

        DepartmentController departmentController =
                new DepartmentController(departmentService);
        ResponseEntity<?> response =
                departmentController.getDepartments();

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

}