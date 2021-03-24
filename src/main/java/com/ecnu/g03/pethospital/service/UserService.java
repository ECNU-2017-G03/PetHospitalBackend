package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.UserTableDao;
import com.ecnu.g03.pethospital.model.entity.Audience;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.util.JwtToken;
import com.ecnu.g03.pethospital.util.JwtUtil;
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

    @Autowired
    Audience audience;

    public boolean loginValidation(String name, String password) {
        UserEntity user = userTableDao.queryUserByNameAndPassword(name, password);
        return user != null;
    }

    public String createJwt(String name) {
        System.out.println(audience);
        String x = JwtUtil.createJWT(name, audience);
        System.out.println(x);
        return x;
    }

    public void validateJwt(String jwt) {
        JwtUtil.parseJWT(jwt, audience.getBase64Secret());
    }
}
