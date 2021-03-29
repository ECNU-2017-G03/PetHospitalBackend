package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.department.DepartmentOverviewResponse;
import com.ecnu.g03.pethospital.service.DepartmentService;
import com.ecnu.g03.pethospital.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:48
 */
@RestController
@RequestMapping("/user/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * @return department name list
     */
    @GetMapping("/list")
    @JwtToken
    public ResponseEntity<?> getDepartments() {
        List<String> departmentList = departmentService.getDepartmentList();
        if (departmentList == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            DepartmentOverviewResponse response = new DepartmentOverviewResponse(departmentList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
