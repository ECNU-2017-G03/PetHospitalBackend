package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.BaseTableDao;
import com.ecnu.g03.pethospital.dto.response.TestResponse;
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
    BaseTableDao baseTableDao;

    @GetMapping("/user/login")
    public TestResponse test() {
        TestResponse response = new TestResponse();
        CloudTableClient y = this.baseTableDao.getTableClient();
        System.out.println(y);
        response.setMessage("hello world!");
        return response;
    }
}
