package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.question.QuestionNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.question.QuestionUpdateRequest;
import com.ecnu.g03.pethospital.dto.admin.response.question.QuestionDeleteResponse;
import com.ecnu.g03.pethospital.dto.admin.response.question.QuestionGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.question.QuestionNewResponse;
import com.ecnu.g03.pethospital.dto.admin.response.question.QuestionUpdateResponse;
import com.ecnu.g03.pethospital.dto.admin.response.question.QuestionSearchResponse;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shen Lei, Xueying Li
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

    @GetMapping("/all")
    public QuestionGetAllResponse getAll() {
        QuestionGetAllResponse response = new QuestionGetAllResponse();
        List<QuestionEntity> questionEntityList = questionService.getAll();
        if (questionEntityList.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuestions(questionEntityList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/delete/{id}")
    public QuestionDeleteResponse deleteById(@PathVariable("id") String id) {
        QuestionDeleteResponse response = new QuestionDeleteResponse();
        if (!questionService.deleteById(id)) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setSuccessful(true);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping("/new")
    public QuestionNewResponse insert(@RequestBody QuestionNewRequest request){
        QuestionNewResponse response = new QuestionNewResponse();
        QuestionEntity entity = questionService.insert(request.getAnswer(),request.getContent(),
                request.getDisease(), request.getOptions());
        if (entity == null) {
            response.setMessage("Cannot add new question");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("Insert question successfully");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setQuestion(entity);
        return response;
    }


    @PostMapping("/update")
    public QuestionUpdateResponse update(@RequestBody QuestionUpdateRequest request) {
        return null;
    }

    @GetMapping("/search/{keyword}")
    public QuestionSearchResponse searchById(@PathVariable("id") String id) {
        QuestionSearchResponse response = new QuestionSearchResponse();
        List<QuestionEntity> questions = new ArrayList<>();
        QuestionEntity question = questionService.getQuestionById(id);
        if (question != null) {
            questions.add(question);
        }
        if (questions.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuestions(questions);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
