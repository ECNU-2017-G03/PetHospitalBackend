package com.ecnu.g03.pethospital.dto.admin.request.question;

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
    private String options;
    private int score;
}
