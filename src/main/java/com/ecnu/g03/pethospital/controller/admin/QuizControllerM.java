package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.quiz.QuizNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.quiz.*;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.service.QuizService;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        System.out.print("start time " + request.getStartTime());
        System.out.print(" end time "+ request.getEndTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = new Date(request.getStartTime());
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.HOUR, -10);
        Date newDate = cl.getTime();
        System.out.println(newDate);
        System.out.println("newDATE");
        String timeNow = sdf.format(newDate);
        System.out.println(timeNow);

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
