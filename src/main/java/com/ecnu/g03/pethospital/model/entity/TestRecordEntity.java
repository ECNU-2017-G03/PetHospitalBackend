package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestRecordServiceEntity;
import lombok.Data;

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

    public TestRecordEntity(String testPaperId, String studentId, String quizId, int score) {
        this.testPaperId = testPaperId;
        this.studentId = studentId;
        this.quizId = quizId;
        this.score = score;
    }

    public static TestRecordEntity fromServiceEntity(TestRecordServiceEntity testRecordServiceEntity) {
        TestRecordEntity testRecordEntity = new TestRecordEntity(testRecordServiceEntity.getPid(), testRecordServiceEntity.getSid(), testRecordServiceEntity.getQuizId(),testRecordServiceEntity.getScore());
        return testRecordEntity;
    }
}
