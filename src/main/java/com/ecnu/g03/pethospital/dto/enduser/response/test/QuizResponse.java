package com.ecnu.g03.pethospital.dto.enduser.response.test;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date Created in 2021/3/24 17:51
 */
@Data
@AllArgsConstructor
public class QuizResponse {
    private List<QuestionEntity> questions;
    private String startTime;
    private String endTime;
    private String quizId;
    private String testId;

    public QuizResponse() {}

    public QuizResponse(String startTime, String endTime, String quizId, String testId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quizId  = quizId;
        this.testId = testId;
        this.questions = new LinkedList<>();
    }
}
