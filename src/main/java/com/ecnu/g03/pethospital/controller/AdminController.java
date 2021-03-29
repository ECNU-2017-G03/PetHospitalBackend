package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.admin.AdminGetAllResponse;
import com.ecnu.g03.pethospital.dto.response.admin.AdminLoginResponse;
import com.ecnu.g03.pethospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 22:39
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login/{name}/{password}")
    public AdminLoginResponse login(@PathVariable("name") String name, @PathVariable("password") String password) {
        return adminService.login(name, password);
    }

    @GetMapping("/all")
    public AdminGetAllResponse getAll() {
        return null;
    }

}
