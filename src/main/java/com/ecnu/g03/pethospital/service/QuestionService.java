package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.QuestionTableDao;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuestionServiceEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 15:28
 */
@Service
public class QuestionService {

    private final QuestionTableDao questionTableDao;

    @Autowired
    QuestionService(QuestionTableDao questionTableDao) {
        this.questionTableDao = questionTableDao;
    }

    public List<QuestionEntity> getAll() {
        return questionTableDao.queryAll();
    }

    public QuestionEntity getQuestionById(String id) {
        return questionTableDao.queryQuestionById(id);
    }

    public QuestionEntity insert(String answer, String content, String disease, Map<String, String> options) {
        QuestionEntity questionEntity = new QuestionEntity(answer, content, disease);
        questionEntity.setOptions(options);
        if (!questionTableDao.insert(questionEntity)) {
            return null;
        }
        return questionEntity;
    }

    public boolean deleteById(String id) {
        return questionTableDao.deleteById(id);
    }

    public List<QuestionEntity> findByIdOrDiseaseOrContent(String keyword) {
        return questionTableDao.queryByIdOrDiseaseOrContentVague(keyword);
    }

    public QuestionEntity update(String id, String answer, String content, String disease, Map<String, String> option) {
        QuestionEntity question = new QuestionEntity(id, answer, content, disease, option, 1);
        return questionTableDao.update(question);
    }


}
