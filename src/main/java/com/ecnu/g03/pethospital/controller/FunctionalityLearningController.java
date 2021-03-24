package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.DiseaseTableDao;
import com.ecnu.g03.pethospital.dto.response.TestResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/24 13:11
 */
@RestController
@CrossOrigin
public class FunctionalityLearningController {
    private static final Gson gson = new Gson();

    private DiseaseTableDao diseaseTableDao;

    @Autowired
    public FunctionalityLearningController(DiseaseTableDao diseaseTableDao) {
        this.diseaseTableDao = diseaseTableDao;
    }

    @GetMapping("/learning/list")
    public TestResponse list() {
        List<DiseaseEntity> diseaseEntities = diseaseTableDao.queryDiseases(5);

        TestResponse response = new TestResponse();
        if (diseaseEntities != null) {
            response.setMessage(gson.toJson(diseaseEntities));
        } else {
            response.setMessage("null");
        }

        return response;
    }
}