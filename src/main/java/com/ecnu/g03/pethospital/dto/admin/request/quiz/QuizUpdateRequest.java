package com.ecnu.g03.pethospital.dto.admin.request.quiz;

import com.ecnu.g03.pethospital.model.parse.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/23 22:58
 */
@Data
public class QuizUpdateRequest {

    private String id;

    private String startTime;
    private String endTime;

    @JsonProperty("testPaper")
    private String testPaperId;

    @JsonProperty("students")
    private List<Student> studentList;

}
