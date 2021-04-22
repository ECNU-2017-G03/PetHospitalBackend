package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.parse.AnswerSnapShot;
import com.ecnu.g03.pethospital.model.serviceentity.TestRecordServiceEntity;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:13
 */
@Data
public class TestRecordEntity extends BaseEntity{
    private String testPaperId;
    private String studentId;
    private String quizId;
    private int score;
    private String submitTime;
    private String startTime;
    private String endTime;
    private int total;
    private List<AnswerSnapShot> answerSnapShot;

    public TestRecordEntity(String partitionKey, String testPaperId, String studentId, String quizId, int score, String submitTime, String startTime, String endTime, int total) {
        super(partitionKey);
        this.testPaperId = testPaperId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
        this.submitTime = submitTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.total = total;
    }

    public TestRecordEntity(String testPaperId, String studentId, String quizId, int score, String submitTime, String startTime, String endTime, int total) {
        super(UUID.randomUUID().toString());
        this.testPaperId = testPaperId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
        this.submitTime = submitTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.total = total;
    }

    public TestRecordEntity(String testPaperId, String studentId, String quizId, int score) {
        this.testPaperId = testPaperId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
    }

    public static TestRecordEntity fromServiceEntity(TestRecordServiceEntity testRecordServiceEntity) {
        TestRecordEntity testRecordEntity = new TestRecordEntity(testRecordServiceEntity.getPartitionKey(),testRecordServiceEntity.getPid(), testRecordServiceEntity.getSid(), testRecordServiceEntity.getQuizId(),testRecordServiceEntity.getScore(),
                testRecordServiceEntity.getSubmitTime(), testRecordServiceEntity.getStartTime(), testRecordServiceEntity.getEndTime(), testRecordServiceEntity.getTotal());
        List<AnswerSnapShot> answerSnapShots = gson.fromJson(testRecordServiceEntity.getAnswerSnapShot(), new TypeToken<List<AnswerSnapShot>>(){}.getType());
        testRecordEntity.setAnswerSnapShot(answerSnapShots);
        return testRecordEntity;
    }

    @Override
    public TestRecordServiceEntity toServiceEntity() {
        TestRecordServiceEntity testRecordServiceEntity= new TestRecordServiceEntity(getId(), getId());
        testRecordServiceEntity.setSid(studentId);
        testRecordServiceEntity.setPid(testPaperId);
        testRecordServiceEntity.setQuizId(quizId);
        testRecordServiceEntity.setScore(score);
        testRecordServiceEntity.setEndTime(endTime);
        testRecordServiceEntity.setStartTime(startTime);
        testRecordServiceEntity.setSubmitTime(submitTime);
        testRecordServiceEntity.setTotal(total);
        String snapShot = gson.toJson(answerSnapShot);
        testRecordServiceEntity.setAnswerSnapShot(snapShot);
        return testRecordServiceEntity;
    }
}
