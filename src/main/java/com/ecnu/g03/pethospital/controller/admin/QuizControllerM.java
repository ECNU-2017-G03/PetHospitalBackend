package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.quiz.QuizNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.*;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.service.QuizService;
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
        SimpleDateFormat outSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        outSdf.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        SimpleDateFormat inSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        inSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            Date startTime = inSdf.parse(request.getStartTime());
            String startTimeInDB = outSdf.format(startTime);
            Date endTime = inSdf.parse(request.getEndTime());
            String endTimeInDB = outSdf.format(endTime);
            Gson gson = new Gson();
            String studentStr = gson.toJson(request.getStudentList());
            QuizEntity entity = quizService.insert(startTimeInDB, endTimeInDB, request.getTestPaperId(), studentStr);
            if (entity == null) {
                response.setStatus(ResponseStatus.DATABASE_ERROR);
                return response;
            }
            response.setStatus(ResponseStatus.SUCCESS);
            response.setQuiz(entity);
            return response;
        } catch (ParseException e) {
            e.printStackTrace();
            response.setStatus(ResponseStatus.BAD_REQUEST);
            return response;
        }
    }

    @GetMapping("/search/{id}")
    public QuizSearchResponse searchByIdAndName(@PathVariable("id") String id) {
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

    @GetMapping("/detail/{id}")
    public QuizDetailResponse searchById(@PathVariable("id") String id) {
        QuizDetailResponse response = new QuizDetailResponse();
        List<QuizEntity> quizzes = quizService.searchById(id);
        if (quizzes.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setQuiz(quizzes.get(0));
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
