package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.dto.admin.response.question.QuestionGetAllResponse;
import com.ecnu.g03.pethospital.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:08
 */
@RestController
@RequestMapping(value = "/admin/question", produces = "application/json; charset=UTF-8")
public class QuestionControllerM {

    private final QuestionService questionService;

    @Autowired
    QuestionControllerM(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public QuestionGetAllResponse getAll() {
        return null;
    }
}
