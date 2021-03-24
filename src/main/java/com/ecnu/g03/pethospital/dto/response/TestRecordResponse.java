package com.ecnu.g03.pethospital.dto.response;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:34
 */
@Data
public class TestRecordResponse {
    private List<QuestionEntity> questionEntityList;
    private int score;
    private String sid;
    private String quizId;

    public TestRecordResponse() {
    }

    public TestRecordResponse(int score, String sid, String quizId) {
        this.score = score;
        this.sid = sid;
        this.quizId = quizId;
        this.questionEntityList = new LinkedList<>();
    }
}
