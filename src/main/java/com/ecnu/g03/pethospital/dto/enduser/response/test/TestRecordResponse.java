package com.ecnu.g03.pethospital.dto.enduser.response.test;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:34
 */
@Data
public class TestRecordResponse {
    private List<QuestionEntity> questionEntityList;
    private String snapShot;
    private int score;
    private int total;
    private String sid;
    private String recordId;
    private String pid;
    private String quizId;

    public TestRecordResponse() {
    }

    public TestRecordResponse(String snapShot, int score, int total, String sid, String recordId, String pid, String quizId) {
        this.questionEntityList = new ArrayList<>();
        this.snapShot = snapShot;
        this.score = score;
        this.total = total;
        this.sid = sid;
        this.recordId = recordId;
        this.pid = pid;
        this.quizId = quizId;
    }

    public TestRecordResponse(List<QuestionEntity> questionEntityList, int score, int total, String sid, String recordId, String pid, String quizId) {
        this.questionEntityList = questionEntityList;
        this.score = score;
        this.total = total;
        this.sid = sid;
        this.recordId = recordId;
        this.pid = pid;
        this.quizId = quizId;
    }

    public TestRecordResponse(int score, String sid, String quizId) {
        this.score = score;
        this.sid = sid;
        this.quizId = quizId;
        this.questionEntityList = new LinkedList<>();
    }
}
