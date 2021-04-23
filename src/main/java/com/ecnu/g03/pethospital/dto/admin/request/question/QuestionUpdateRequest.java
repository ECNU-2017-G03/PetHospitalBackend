package com.ecnu.g03.pethospital.dto.admin.request.question;

import lombok.Data;

import java.util.Map;

/**
 * @author Shen Lei
 * @date 2021/4/14 14:28
 */
@Data
public class QuestionUpdateRequest {

    private String answer;
    private String content;
    private String disease;
    private String id;
    private Map<String, String> option;

}
