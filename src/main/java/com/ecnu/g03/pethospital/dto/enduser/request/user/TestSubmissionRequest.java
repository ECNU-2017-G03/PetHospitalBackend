package com.ecnu.g03.pethospital.dto.enduser.request.user;

import com.ecnu.g03.pethospital.model.parse.TestQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/4/7 15:01
 */
@Data
@AllArgsConstructor
public class TestSubmissionRequest {
    private String quizId;
    private String startTime;
    private String endTime;
    private String testId;
    private List<TestQuestion> questions;
}
