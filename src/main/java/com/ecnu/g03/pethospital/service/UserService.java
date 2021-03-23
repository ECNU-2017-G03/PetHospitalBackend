package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.UserTableDao;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiayi Zhu
 * @date 2021-03-23 22:45
 */
@Service
public class UserService {
    @Autowired
    UserTableDao userTableDao;

    public boolean loginValidation(String name, String password) {
        UserEntity user = userTableDao.queryUserByNameAndPassword(name, password);
        return user != null;
    }
}
