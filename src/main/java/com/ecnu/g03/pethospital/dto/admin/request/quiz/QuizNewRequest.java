package com.ecnu.g03.pethospital.dto.admin.request.quiz;

import com.ecnu.g03.pethospital.model.parse.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/12 10:10
 */
@Data
public class QuizNewRequest {

    private String startTime;
    private String endTime;

    @JsonProperty("testPaper")
    private String testPaperId;

    @JsonProperty("students")
    private List<Student> studentList;
}
