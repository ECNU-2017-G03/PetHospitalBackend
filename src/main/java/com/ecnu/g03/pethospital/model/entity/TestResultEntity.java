package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.parse.QuestionRecord;
import com.ecnu.g03.pethospital.model.serviceentity.TestResultServiceEntity;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:45
 */
@Data
public class TestResultEntity extends BaseEntity{
    private List<QuestionRecord> recordList;
    private String studentId;
    private String testPaperId;

    public TestResultEntity(String studentId, String testPaperId) {
        this.studentId = studentId;
        this.testPaperId = testPaperId;
    }

    public static TestResultEntity fromServiceEntity(TestResultServiceEntity testResultServiceEntity) {
        TestResultEntity testResultEntity = new TestResultEntity(testResultServiceEntity.getSid(), testResultServiceEntity.getPid());
        String qAnswers = testResultServiceEntity.getQAnswer();
        testResultEntity.setRecordList(gson.fromJson(qAnswers, new TypeToken<List<QuestionRecord>>(){}.getType()));
        return testResultEntity;
    }
}
