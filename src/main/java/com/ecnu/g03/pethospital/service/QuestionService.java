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

    public QuestionEntity insert(String answer, String content, String disease, String option) {
        Gson gson = new Gson();
        QuestionEntity questionEntity = new QuestionEntity(answer, content, disease);
        questionEntity.setOptions(gson.fromJson(option, new TypeToken<HashMap<String, String>>(){}.getType()));
        if (!questionTableDao.insert(questionEntity)) {
            return null;
        }
        return questionEntity;
    }

    public boolean deleteById(String id) {
        return questionTableDao.deleteById(id);
    }

}
