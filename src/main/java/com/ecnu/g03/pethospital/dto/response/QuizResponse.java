package com.ecnu.g03.pethospital.dto.response;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 17:51
 */
@Data
public class QuizResponse {
    private List<QuestionEntity> questions;
    private String startTime;
    private String endTime;
    private String quizId;

    public QuizResponse() {}

    public QuizResponse(String startTime, String endTime, String quizId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quizId  = quizId;
        this.questions = new LinkedList<>();
    }
}
