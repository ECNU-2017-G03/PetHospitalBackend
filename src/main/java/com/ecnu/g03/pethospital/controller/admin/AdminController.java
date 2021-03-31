package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.admin.AdminNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminDetailResponse;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminLoginResponse;
import com.ecnu.g03.pethospital.dto.admin.response.admin.AdminNewResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/28 22:39
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * @param request includes username and password, decided by super admin
     * @return {@link AdminNewResponse}
     */
    @PostMapping("/new")
    public AdminNewResponse insertAdmin(@RequestBody AdminNewRequest request) {
        AdminNewResponse response = new AdminNewResponse();
        /* only super admin can insert new admin */
        AdminEntity operator = adminService.queryById(request.getOperator());
        if (operator.getRole() != AdminRole.SUPER) {
            response.setMessage("No privilege");
            response.setStatus(ResponseStatus.AUTHORIZATION_ERROR);
            response.setAdmin(null);
            return response;
        }
        AdminEntity adminEntity = adminService.insert(request.getName(), request.getPassword());
        if (adminEntity == null) {
            response.setMessage("Cannot add admin");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("New user added");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmin(adminEntity);
        return response;
    }

    /**
     * @param id the user whose detail information will be queried
     * @return {@link AdminDetailResponse}
     */
    @GetMapping("/detail")
    public AdminDetailResponse getDetail(@PathVariable("id") String id) {
        AdminDetailResponse response = new AdminDetailResponse();
        AdminEntity adminEntity = adminService.queryById(id);
        if (adminEntity == null) {
            response.setMessage("Cannot find user");
            response.setStatus(ResponseStatus.NO_DATA);
            response.setAdmin(null);
            return response;
        }
        response.setMessage("Request successful");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmin(adminEntity);
        return response;
    }

    /**
     * @param name username
     * @param password password
     * @return {@link AdminLoginResponse}
     */
    @GetMapping("/login/{name}/{password}")
    public AdminLoginResponse login(@PathVariable("name") String name, @PathVariable("password") String password) {
        AdminLoginResponse response = new AdminLoginResponse();
        AdminEntity adminEntity = adminService.login(name, password);
        if (adminEntity == null) {
            response.setMessage("Wrong username or password!");
            response.setStatus(com.ecnu.g03.pethospital.constant.ResponseStatus.SUCCESS);
            response.setIsSuccessful(false);
            response.setAdmin(null);
        } else {
            response.setMessage("Login successful");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setIsSuccessful(true);
            response.setAdmin(adminEntity);
        }
        return response;
    }

    /**
     * @return a list of all admins {@link AdminGetAllResponse}
     */
    @GetMapping("/all")
    public AdminGetAllResponse getAll() {
        AdminGetAllResponse response = new AdminGetAllResponse();
        List<AdminEntity> adminEntities = adminService.getAll();
        if (adminEntities.size() > 0) {
            response.setMessage("Request successful");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setAdmins(adminEntities);
        } else {
            response.setMessage("No admin found");
            response.setStatus(ResponseStatus.NO_DATA);
            response.setAdmins(null);
        }
        return response;
    }

}
