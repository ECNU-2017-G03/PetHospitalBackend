package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.dao.table.AdminTableDao;
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

    public AdminEntity queryByName(String name) {
        return adminTableDao.queryByName(name);
    }

    public boolean deleteById(String id) {
        return adminTableDao.deleteById(id);
    }

    public AdminEntity resetPasswordRandom(String id) {
        Integer random = (int) (Math.random()*10000000);
        String password = String.valueOf(random);
        AdminEntity admin = adminTableDao.queryById(id);
        admin.setPassword(password);
        return adminTableDao.update(admin);
    }

    public AdminEntity resetPassword(String name, String newPassword) {
        AdminEntity admin = adminTableDao.queryByName(name);
        admin.setPassword(newPassword);
        return adminTableDao.update(admin);
    }

    public AdminEntity updateRoleById(String id) {
        AdminEntity current = adminTableDao.queryById(id);
        if (current.getRole() == AdminRole.SUPER) {
            current.setRole(AdminRole.NORMAL);
        } else {
            current.setRole(AdminRole.SUPER);
        }
        return adminTableDao.update(current);
    }

}
