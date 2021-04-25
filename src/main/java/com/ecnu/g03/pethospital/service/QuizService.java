package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.QuizTableDao;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.model.parse.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 15:28
 */
@Service
public class QuizService {

    private final QuizTableDao quizTableDao;

    QuizService(QuizTableDao quizTableDao) {
        this.quizTableDao = quizTableDao;
    }

    public List<QuizEntity> getAll() {
        return quizTableDao.queryAll();
    }

    public QuizEntity getQuizById(String id) {
        return quizTableDao.queryQuizById(id);
    }

    public QuizEntity insert(String startTime, String endTime, String pid, String students) {
        QuizEntity quizEntity = new QuizEntity(startTime, endTime, pid);
        Gson gson = new Gson();
        quizEntity.setStudentIdList(gson.fromJson(students, new TypeToken<List<Student>>(){}.getType()));
        if (!quizTableDao.insert(quizEntity)) {
            return null;
        }
        return quizEntity;
    }

    public QuizEntity update(String id, String startTime, String endTime, String pid, List<Student> students) {
        QuizEntity quizEntity = new QuizEntity(id, startTime, endTime, pid, students);
        return quizTableDao.update(quizEntity);
    }

    public boolean deleteById(String id) {
        return quizTableDao.deleteById(id);
    }

    public List<QuizEntity> searchById(String id) {
        List<QuizEntity> quizzes = new ArrayList<>();
        QuizEntity quiz = quizTableDao.queryQuizById(id);
        if (quiz != null) {
            quizzes.add(quiz);
        }
        return quizzes;
    }

    public List<QuizEntity> searchByIdVague(String id) {
        return quizTableDao.queryQuizByIdVague(id);
    }

}
