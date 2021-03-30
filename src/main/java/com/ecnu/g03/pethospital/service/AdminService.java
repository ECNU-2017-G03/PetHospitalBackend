package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dao.AdminTableDao;
import com.ecnu.g03.pethospital.dto.response.admin.AdminGetAllResponse;
import com.ecnu.g03.pethospital.dto.response.admin.AdminLoginResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 22:39
 */
@Service
public class AdminService {

    @Autowired
    AdminTableDao adminTableDao;

    public AdminLoginResponse login(String name, String password) {
        AdminLoginResponse response = new AdminLoginResponse();
        AdminEntity adminEntity = adminTableDao.queryByNameAndPassword(name, password);
        if (adminEntity == null) {
            response.setMessage("Wrong username or password!");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setIsSuccessful(false);
            response.setRole(null);
        } else {
            response.setMessage("Login successful");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setIsSuccessful(true);
            response.setRole(adminEntity.getRole());
        }
        return response;
    }

    public AdminGetAllResponse getAll() {
        AdminGetAllResponse response = new AdminGetAllResponse();
        List<AdminEntity> adminEntities = adminTableDao.queryAll();
        if (adminEntities.size() > 0) {
            response.setMessage("Request successful");
            response.setStatus(ResponseStatus.SUCCESS);
            response.setAdmins(adminEntities);
        } else if (adminEntities.size() == 0) {
            response.setMessage("Request successful");
            response.setStatus(ResponseStatus.NO_DATA);
            response.setAdmins(null);
        } else {
            response.setMessage("Request failed");
            response.setStatus(ResponseStatus.UNKNOWN_ERROR);
            response.setAdmins(null);
        }
        return response;
    }

}
