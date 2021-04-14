package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.quiz.QuizNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.QuizDeleteResponse;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.QuizGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.QuizNewResponse;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.QuizSearchResponse;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.service.QuizService;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 13:08
 */
@RestController
@RequestMapping(value = "/admin/quiz", produces = "application/json; charset=UTF-8")
public class QuizControllerM {

    private final QuizService quizService;

    QuizControllerM(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/all")
    public QuizGetAllResponse getAll() {
        QuizGetAllResponse response = new QuizGetAllResponse();
        List<QuizEntity> quizEntityList = quizService.getAll();
        if (quizEntityList.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuizzes(quizEntityList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/delete/{id}")
    public QuizDeleteResponse deleteById(@PathVariable("id") String id) {
        QuizDeleteResponse response = new QuizDeleteResponse();
        if (!quizService.deleteById(id)) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setSuccessful(true);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @PostMapping("/new")
    public QuizNewResponse insert(@RequestBody QuizNewRequest request) {
        QuizNewResponse response = new QuizNewResponse();
        Gson gson = new Gson();
        String studentStr = gson.toJson(request.getStudentList());
        QuizEntity entity = quizService.insert(request.getStartTime(), request.getEndTime(), request.getTestPaperId(), studentStr);
        if (entity == null) {
            response.setMessage("Cannot add new quiz");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("Insert quiz successfully");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setQuiz(entity);
        return response;
    }

    @GetMapping("/search/{keyword}")
    public QuizSearchResponse searchById(@PathVariable("id") String id) {
        QuizSearchResponse response = new QuizSearchResponse();
        List<QuizEntity> quizzes = quizService.searchById(id);
        if (quizzes.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuizzes(quizzes);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
