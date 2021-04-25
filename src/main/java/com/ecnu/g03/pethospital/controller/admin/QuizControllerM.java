package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.quiz.QuizNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.quiz.QuizUpdateRequest;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.*;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.service.QuizService;
import com.ecnu.g03.pethospital.util.TimeConverter;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
        /* convert time format */
        quizEntityList.forEach(q->{
            q.setEndTime(TimeConverter.DBTimeToUI(q.getEndTime()));
            q.setStartTime(TimeConverter.DBTimeToUI(q.getStartTime()));
        });
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
        String startTime = TimeConverter.UITimeToDB(request.getStartTime());
        String endTime = TimeConverter.UITimeToDB(request.getEndTime());
        if (startTime == null || endTime == null) {
            response.setStatus(ResponseStatus.BAD_REQUEST);
            return response;
        }
        Gson gson = new Gson();
        String studentStr = gson.toJson(request.getStudentList());
        QuizEntity entity = quizService.insert(startTime, endTime, request.getTestPaperId(), studentStr);
        if (entity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setQuiz(entity);
        return response;
    }

    @PostMapping("/update")
    public QuizUpdateResponse update(@RequestBody QuizUpdateRequest request) {
        QuizUpdateResponse response = new QuizUpdateResponse();
        String startTime = TimeConverter.UITimeToDB(request.getStartTime());
        String endTime = TimeConverter.UITimeToDB(request.getEndTime());
        if (startTime == null || endTime == null) {
            response.setStatus(ResponseStatus.BAD_REQUEST);
            return response;
        }
        Gson gson = new Gson();
        String studentStr = gson.toJson(request.getStudentList());
        QuizEntity entity = quizService.update(request.getId(), startTime, endTime, request.getTestPaperId(), request.getStudentList());
        if (entity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setQuiz(entity);
        return response;

    }

    @GetMapping("/search/{id}")
    public QuizSearchResponse searchByIdAndName(@PathVariable("id") String id) {
        QuizSearchResponse response = new QuizSearchResponse();
        List<QuizEntity> quizzes = quizService.searchByIdVague(id);
        quizzes.forEach(q->{
            q.setEndTime(TimeConverter.DBTimeToUI(q.getEndTime()));
            q.setStartTime(TimeConverter.DBTimeToUI(q.getStartTime()));
        });
        if (quizzes.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuizzes(quizzes);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/detail/{id}")
    public QuizDetailResponse searchById(@PathVariable("id") String id) {
        QuizDetailResponse response = new QuizDetailResponse();
        List<QuizEntity> quizzes = quizService.searchById(id);
        quizzes.forEach(q->{
            q.setEndTime(TimeConverter.DBTimeToUI(q.getEndTime()));
            q.setStartTime(TimeConverter.DBTimeToUI(q.getStartTime()));
        });
        if (quizzes.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuiz(quizzes.get(0));
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
