package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.QuestionTableDao;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuestionServiceEntity;
import com.microsoft.azure.storage.table.TableOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Shen Lei
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

    public boolean insert(String answer, String content, String disease, String option) {
        String uuid = UUID.randomUUID().toString();
        QuestionServiceEntity questionServiceEntity = new QuestionServiceEntity(uuid, uuid);
        questionServiceEntity.setAnswer(answer);
        questionServiceEntity.setContent(content);
        questionServiceEntity.setDisease(disease);
        questionServiceEntity.setOption(option);
        questionServiceEntity.setScore(0);
        return questionTableDao.insert(questionServiceEntity);
    }

    public boolean deleteById(String id) {
        return questionTableDao.deleteById(id);

    }

}
