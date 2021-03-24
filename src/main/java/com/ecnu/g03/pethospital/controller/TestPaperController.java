package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.TestPaperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 13:31
 */
@RestController
@CrossOrigin
public class TestPaperController {
    @Autowired
    TestPaperDao testPaperDao;

    @GetMapping("/testPaper")
    public String getPaper() {
        testPaperDao.queryTestPaper("88115aa98-d366-455d-a8bb-9dbe0b99d310");
        return "success";
    }
}
