package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminLoginResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 22:39
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login/{name}/{password}")
    public AdminLoginResponse login(@PathVariable("name") String name, @PathVariable("password") String password) {
        AdminLoginResponse response = new AdminLoginResponse();
        AdminEntity admin = adminService.login(name, password);
        if (admin == null) {
            response.setMessage("Wrong password or username");
            response.setStatus(ResponseStatus.AUTHORIZATION_ERROR);
            response.setIsSuccessful(false);
            return response;
        }
        response.setMessage("Login successful");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setIsSuccessful(true);
        response.setAdmin(admin);
        return response;
    }

    @GetMapping("/all")
    public AdminGetAllResponse getAll() {
        AdminGetAllResponse response = new AdminGetAllResponse();
        List<AdminEntity> admins = adminService.getAll();
        if (admins.size() == 0) {
            response.setMessage("No admins found");
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setMessage("Query successful");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmins(admins);
        return response;
    }

}
