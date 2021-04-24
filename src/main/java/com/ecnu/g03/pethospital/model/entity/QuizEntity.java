package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.parse.Student;
import com.ecnu.g03.pethospital.model.serviceentity.QuizServiceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author Yiqing Tao, Xueying Li
 * @date Created in 2021/3/24 17:38
 */
@Data
public class QuizEntity extends BaseEntity{
    private String startTime;
    private String endTime;
    @JsonProperty("testPaper")
    private String testPaperId;
    @JsonProperty("id")
    private String quizId;
    @JsonProperty("students")
    private List<Student> studentIdList;

    public QuizEntity(String startTime, String endTime, String testPaperId) {
        super(UUID.randomUUID().toString());
        quizId = this.getId();
        this.startTime = startTime;
        this.endTime = endTime;
        this.testPaperId = testPaperId;
    }

    public QuizEntity(String startTime, String endTime, String testPaperId, String quizId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.testPaperId = testPaperId;
        this.quizId = quizId;
    }

    public QuizEntity(String id, String startTime, String endTime, String testPaperId, List<Student> studentIdList) {
        super(id);
        this.startTime = startTime;
        this.endTime = endTime;
        this.testPaperId = testPaperId;
        this.studentIdList = studentIdList;
    }

    public static QuizEntity fromServiceEntity(QuizServiceEntity quizServiceEntity) {
        QuizEntity quizEntity = new QuizEntity(quizServiceEntity.getStartTime(), quizServiceEntity.getEndTime(), quizServiceEntity.getPid(), quizServiceEntity.getPartitionKey());
        String students = quizServiceEntity.getStudents();
        quizEntity.setStudentIdList(gson.fromJson(students, new TypeToken<List<Student>>(){}.getType()));
        return quizEntity;
    }

    @Override
    public QuizServiceEntity toServiceEntity() {
        String id = getId();
        QuizServiceEntity quizServiceEntity = new QuizServiceEntity(id, id);
        quizServiceEntity.setStartTime(startTime);
        quizServiceEntity.setEndTime(endTime);
        quizServiceEntity.setPid(testPaperId);
        quizServiceEntity.setStudents(gson.toJson(studentIdList));
        return quizServiceEntity;
    }

}
