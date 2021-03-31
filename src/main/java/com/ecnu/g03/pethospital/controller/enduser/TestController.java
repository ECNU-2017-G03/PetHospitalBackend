package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.dto.enduser.response.test.PastTestResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.QuizResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestReadyResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestRecordResponse;
import com.ecnu.g03.pethospital.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yiqing Tao
 * @date Created in 2021/3/24 13:31
 */
@RestController
@RequestMapping("/user/test")
public class TestController {
    private UserTestService userTestService;

    @Autowired
    public TestController(UserTestService userTestService) {
        this.userTestService = userTestService;
    }

    @GetMapping(value = "enterTest", params = {"id"})
    public ResponseEntity<?> getQuizPaper(@RequestParam("id") String id) {
        QuizResponse quizResponse = userTestService.getQuizById(id);
        return new ResponseEntity<>(quizResponse, HttpStatus.OK);
    }

    //todo: replace sid with function to get id

    /**
     * get past specific test record by test paper id and student id
     * @param id paper id
     * @param sid student id
     * @return test record response
     */
    @GetMapping(value = "pastTest")
    public ResponseEntity<?> getPastTestRecordByQuizId(@RequestParam("id") String id, @RequestParam("sid") String sid) {
        TestRecordResponse testRecordResponse = userTestService.getTestRecordByQuizIdAndSid(sid, id);
        return new ResponseEntity<>(testRecordResponse, HttpStatus.OK);
    }

    //todo: replace id with function to get id
    @GetMapping(value = "testRecord")
    public ResponseEntity<?> getPastTestRecord(@RequestParam("id") String id) {
        PastTestResponse pastTestResponse = userTestService.getPastTestBySid(id);
        return new ResponseEntity<>(pastTestResponse, HttpStatus.OK);
    }

    @GetMapping(value = "enterTestFunc")
    public ResponseEntity<?> getTestInfo(@RequestParam("id") String id) {
        TestReadyResponse testReadyResponse = userTestService.getTestForUser(id);
        return new ResponseEntity<>(testReadyResponse, HttpStatus.OK);
    }

}
