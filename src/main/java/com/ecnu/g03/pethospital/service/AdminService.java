package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.dao.AdminTableDao;
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

    public AdminEntity login(String name, String password) {
        return adminTableDao.queryByNameAndPassword(name, password);
    }

    public List<AdminEntity> getAll() {
        return adminTableDao.queryAll();
    }

    public AdminEntity insert(String name, String password) {
        AdminEntity adminEntity = new AdminEntity(name, password, AdminRole.NORMAL);
        if (adminTableDao.insert(adminEntity)) {
            return adminEntity;
        }
        return null;
    }

    public AdminEntity queryById(String id) {
        return adminTableDao.queryById(id);
    }

}
