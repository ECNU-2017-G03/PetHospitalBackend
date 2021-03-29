package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.department.DepartmentDetailResponse;
import com.ecnu.g03.pethospital.dto.response.department.DepartmentOverviewResponse;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.ecnu.g03.pethospital.service.DepartmentService;
import com.ecnu.g03.pethospital.util.JwtToken;
import com.ecnu.g03.pethospital.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<DepartmentServiceEntity> departmentList = departmentService.getDepartmentList();
        if (departmentList == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            DepartmentOverviewResponse response = new DepartmentOverviewResponse(departmentList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/detail", params = {"id", "actor"})
    @JwtToken
    public ResponseEntity<?> getDepartmentDetail(@RequestParam("id") String id,
                                                 @RequestParam("actor") String actor,
                                                 @RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        if (!JwtUtil.checkActorValid(token, actor)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        DepartmentDetailResponse detail = departmentService.getDepartmentDetail(id, actor);
        if (detail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(detail, HttpStatus.OK);
        }
    }
}
