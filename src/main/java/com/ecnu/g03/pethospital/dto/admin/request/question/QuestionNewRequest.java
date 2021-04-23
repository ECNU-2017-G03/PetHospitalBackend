package com.ecnu.g03.pethospital.dto.admin.request.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author Xueying Li
 * @date 2021/4/12 08:52
 */
@Data
public class QuestionNewRequest {
    private String answer;
    private String content;
    private String disease;
    @JsonProperty("option")
    private Map<String, String> options;
    private int score;
}
