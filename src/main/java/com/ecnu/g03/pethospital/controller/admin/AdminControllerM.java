package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.admin.AdminLoginRequest;
import com.ecnu.g03.pethospital.dto.admin.request.admin.AdminNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.admin.AdminResetPassRequest;
import com.ecnu.g03.pethospital.dto.admin.response.admin.*;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/28 22:39
 */
@RestController
@RequestMapping(value = "/admin", produces = "application/json; charset=UTF-8")
public class AdminControllerM {

    private final AdminService adminService;

    @Autowired
    public AdminControllerM(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * @param request includes username and password, decided by super admin
     * @return {@link AdminNewResponse}
     */
    @PostMapping("/new")
    public AdminNewResponse insert(@RequestBody AdminNewRequest request) {
        System.out.println("name" + request.getName());
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

    @GetMapping("/test/{id}")
    public AdminGetAllResponse test(@PathVariable("id") String id) {
        AdminEntity operator = adminService.queryById(id);
        return null;
    }

    /**
     * @param id the user whose detail information will be queried
     * @return {@link AdminDetailResponse}
     */
    @GetMapping("/detail/{id}")
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
     * @param request {@link AdminLoginRequest}
     * @return {@link AdminLoginResponse}
     */
    @PostMapping("/login")
    public AdminLoginResponse login(@RequestBody AdminLoginRequest request) {
        System.out.println("name: " + request.getUserName());
        System.out.println("key: " + request.getUserKey());
        AdminLoginResponse response = new AdminLoginResponse();
        AdminEntity adminEntity = adminService.login(request.getUserName(), request.getUserKey());
        if (adminEntity == null) {
            response.setMessage("Wrong username or password!");
            response.setStatus(ResponseStatus.AUTHORIZATION_ERROR);
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

    @GetMapping("/delete/{id}")
    public AdminDeleteResponse delete(@PathVariable("id") String id) {
        AdminDeleteResponse response = new AdminDeleteResponse();
        if (!adminService.deleteById(id)) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            response.setSuccessful(false);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setSuccessful(true);
        return response;
    }

    @GetMapping("/update/role/{id}")
    public AdminUpdateResponse updateRole(@PathVariable("id") String id) {
        AdminUpdateResponse response = new AdminUpdateResponse();
        AdminEntity admin = adminService.updateRoleById(id);
        if (admin == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmin(admin);
        return response;
    }

    /**
     * reset admin password to a random value
     * @return {@link AdminUpdateResponse}
     */
    @GetMapping("/reset/password/{id}")
    public AdminUpdateResponse updatePassword(@PathVariable("id") String id) {
        AdminUpdateResponse response = new AdminUpdateResponse();
        AdminEntity admin = adminService.resetPasswordRandom(id);
        if (admin == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmin(admin);
        return response;
    }

    /**
     * search admin info by name or id
     * @param keyword part of name or id
     * @return a list of matched admins
     */
    @GetMapping("/search/{keyword}")
    public AdminSearchResponse searchByIdAndName(@PathVariable("keyword") String keyword) {
        AdminSearchResponse response = new AdminSearchResponse();
        List<AdminEntity> admins = adminService.findByIdOrNameVague(keyword);
        if (admins.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmins(admins);
        return response;
    }

    @PostMapping("/reset/password/byself")
    public AdminUpdateResponse updatePasswordBySelf(@RequestBody AdminResetPassRequest request) {
        AdminUpdateResponse response = new AdminUpdateResponse();
        /* validate old password*/
        AdminEntity adminEntity = adminService.login(request.getUserName(), request.getOldPassword());
        if (adminEntity == null) {
            response.setStatus(ResponseStatus.AUTHORIZATION_ERROR);
            response.setAdmin(null);
            return response;
        }
        /* update password in database */
        AdminEntity admin = adminService.resetPassword(request.getUserName(), request.getNewPassword());
        if (admin == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setAdmin(admin);
        return response;
    }

}
