package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.test.PastTestResponse;
import com.ecnu.g03.pethospital.dto.response.test.QuizResponse;
import com.ecnu.g03.pethospital.dto.response.test.TestReadyResponse;
import com.ecnu.g03.pethospital.dto.response.test.TestRecordResponse;
import com.ecnu.g03.pethospital.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 13:31
 */
@RestController
@CrossOrigin
public class TestController {
    private UserTestService userTestService;

    @Autowired
    public TestController(UserTestService userTestService) {
        this.userTestService = userTestService;
    }

    @GetMapping(value = "user/test/enterTest", params = {"id"})
    public QuizResponse getQuizPaper(@RequestParam("id") String id) {
        return userTestService.getQuizById(id);
    }

    //todo: replace sid with function to get id

    /**
     * get past specific test record by test paper id and student id
     * @param id paper id
     * @param sid student id
     * @return test record response
     */
    @GetMapping(value = "user/test/pastTest")
    public TestRecordResponse getPastTestRecordByQuizId(@RequestParam("id") String id, @RequestParam("sid") String sid) {
        return userTestService.getTestRecordByQuizIdAndSid(sid, id);
    }

    //todo: replace id with function to get id
    @GetMapping(value = "user/test/testRecord")
    public PastTestResponse getPastTestRecord(@RequestParam("id") String id) {
        return userTestService.getPastTestBySid(id);
    }

    @GetMapping(value = "user/test/enterTestFunc")
    public TestReadyResponse getTestInfo(@RequestParam("id") String id) {
        return userTestService.getTestForUser(id);
    }

}
