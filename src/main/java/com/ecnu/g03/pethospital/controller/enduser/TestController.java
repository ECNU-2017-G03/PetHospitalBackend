package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.dto.enduser.request.user.TestSubmissionRequest;
import com.ecnu.g03.pethospital.dto.enduser.response.test.PastTestResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.QuizResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestReadyResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestRecordResponse;
import com.ecnu.g03.pethospital.model.status.SubmitTestStatus;
import com.ecnu.g03.pethospital.service.UserTestService;
import com.ecnu.g03.pethospital.util.JwtToken;
import com.ecnu.g03.pethospital.util.JwtUtil;
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

    @GetMapping(value = "checkTestValid", params = {"id"})
    @JwtToken
    public ResponseEntity<?> checkTestValid(@RequestParam("id") String id,
                                            @RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        String sid = JwtUtil.getUserId(token);
        boolean valid = userTestService.checkTestValid(sid, id);
        return new ResponseEntity<>(valid, HttpStatus.OK);
    }

    @GetMapping(value = "enterTest", params = {"id"})
    @JwtToken
    public ResponseEntity<?> getQuizPaper(@RequestParam("id") String id) {
        QuizResponse quizResponse = userTestService.getQuizById(id, false);
        return new ResponseEntity<>(quizResponse, HttpStatus.OK);
    }

    @PostMapping(value = "submitTest")
    @JwtToken
    public SubmitTestStatus submitTest(@RequestBody TestSubmissionRequest testSubmissionRequest,
                                 @RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        String id = JwtUtil.getUserId(token);
        SubmitTestStatus status = userTestService.submitTest(testSubmissionRequest.getQuestions(), testSubmissionRequest.getTestId(),
                id, testSubmissionRequest.getQuizId(), testSubmissionRequest.getStartTime(), testSubmissionRequest.getEndTime());
        return status;
    }

    /**
     * get past specific test record by test paper id and student id
     * @param id quiz id
     * @return test record response
     */
    @GetMapping(value = "pastTest")
    @JwtToken
    public ResponseEntity<?> getPastTestRecordByQuizId(@RequestParam("id") String id,
                                                       @RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        QuizResponse quizResponse = userTestService.getQuizById(id, true);
        return new ResponseEntity<>(quizResponse, HttpStatus.OK);
    }

    @GetMapping(value = "testRecord")
    @JwtToken
    public ResponseEntity<?> getPastTestRecord( @RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        String sid = JwtUtil.getUserId(token);
        PastTestResponse pastTestResponse = userTestService.getPastTestBySid(sid);
        return new ResponseEntity<>(pastTestResponse, HttpStatus.OK);
    }


    @GetMapping(value = "enterTestFunc")
    @JwtToken
    public ResponseEntity<?> getTestInfo(@RequestHeader(JwtUtil.AUTH_HEADER_KEY) String auth) {
        String token = auth.substring(JwtUtil.TOKEN_PREFIX.length());
        String sid = JwtUtil.getUserId(token);
        TestReadyResponse testReadyResponse = userTestService.getTestForUser(sid);
        return new ResponseEntity<>(testReadyResponse, HttpStatus.OK);
    }
}
