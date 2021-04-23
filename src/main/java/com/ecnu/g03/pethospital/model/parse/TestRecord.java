package com.ecnu.g03.pethospital.model.parse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Yiqing Tao
 * @date Created in 2021/4/7 21:27
 */
@Data
public class TestRecord {
    private String recordId;
    private String testId;
    private String quizId;
    private String startTime;
    private String submitTime;
    private String sid;
    private int total;
    private int score;
    private String endTime;
    private List<AnswerSnapShot> snapShot;

    public TestRecord(String recordId, String testId, String quizId, String startTime, String submitTime, String sid, int total, int score, String endTime, List<AnswerSnapShot> snapShot) {
        this.recordId = recordId;
        this.testId = testId;
        this.quizId = quizId;
        this.startTime = startTime;
        this.submitTime = submitTime;
        this.sid = sid;
        this.total = total;
        this.score = score;
        this.endTime = endTime;
        this.snapShot = snapShot;
    }
}
