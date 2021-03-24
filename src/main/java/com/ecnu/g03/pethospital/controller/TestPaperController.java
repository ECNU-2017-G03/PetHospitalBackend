package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.TestPaperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 13:31
 */
@RestController
@CrossOrigin
public class TestPaperController {
    @Autowired
    TestPaperDao testPaperDao;

    @GetMapping(value = "user/test/enterTest", params = {"id"})
    public void queryTestPaperById(@RequestParam("id") String id) {
        testPaperDao.queryTestPaper(id);
    }
}
