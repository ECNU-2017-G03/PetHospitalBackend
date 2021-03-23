package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.BaseTableDao;
import com.ecnu.g03.pethospital.dao.UserTableDao;
import com.ecnu.g03.pethospital.dto.response.TestResponse;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * @date 2021-03-22 10:27
 */
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserTableDao userTableDao;

    @GetMapping("/user/login")
    public TestResponse test() {
        UserEntity u = userTableDao.queryUserByName("momo");
        System.out.println(u);
        System.out.println(u.getPassword());
        System.out.println(u.getActor());

        TestResponse response = new TestResponse();
        response.setMessage("hello world!");
        return response;
    }
}
