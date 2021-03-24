package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.DiseaseCaseTableDao;
import com.ecnu.g03.pethospital.dao.DiseaseTableDao;
import com.ecnu.g03.pethospital.dto.response.TestResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private DiseaseCaseTableDao diseaseCaseTableDao;

    @Autowired
    public FunctionalityLearningController(
            DiseaseTableDao diseaseTableDao,
            DiseaseCaseTableDao diseaseCaseTableDao
    ) {
        this.diseaseTableDao = diseaseTableDao;
        this.diseaseCaseTableDao = diseaseCaseTableDao;
    }

    @GetMapping("/learning/listDisease")
    public TestResponse listDisease() {
        List<DiseaseEntity> diseaseEntities = diseaseTableDao.queryDiseases(5);

        TestResponse response = new TestResponse();
        if (diseaseEntities != null) {
            response.setMessage(gson.toJson(diseaseEntities));
        } else {
            response.setMessage("null");
        }

        return response;
    }

    @GetMapping(value = "learning/queryDisease", params = {"id"})
    public TestResponse queryDiseaseById(@RequestParam("id") String id) {
        DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseById(id);

        TestResponse response = new TestResponse();
        if (diseaseEntity != null) {
            response.setMessage(gson.toJson(diseaseEntity));
        } else {
            response.setMessage("null");
        }

        return response;
    }

    @GetMapping(value = "learning/queryDisease", params = {"name"})
    public TestResponse queryDiseaseByName(@RequestParam("name") String name) {
        DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseByName(name);

        TestResponse response = new TestResponse();
        if (diseaseEntity != null) {
            response.setMessage(gson.toJson(diseaseEntity));
        } else {
            response.setMessage("null");
        }

        return response;
    }

    @GetMapping(value = "learning/listDiseaseCase")
    public TestResponse listDiseaseCase() {
        List<DiseaseCaseEntity> diseaseCaseEntities
                = diseaseCaseTableDao.queryDiseaseCases(5);

        TestResponse response = new TestResponse();
        if (diseaseCaseEntities != null) {
            response.setMessage(gson.toJson(diseaseCaseEntities));
        } else {
            response.setMessage("null");
        }

        return response;
    }

    @GetMapping(value = "learning/queryDiseaseCase", params = {"id"})
    public TestResponse queryDiseaseCaseById(@RequestParam("id") String id) {
        DiseaseCaseEntity diseaseCaseEntity = diseaseCaseTableDao.queryDiseaseCaseById(id);

        TestResponse response = new TestResponse();
        if (diseaseCaseEntity != null) {
            response.setMessage(gson.toJson(diseaseCaseEntity));
        } else {
            response.setMessage("null");
        }

        return response;
    }

    @GetMapping(value = "learning/queryDiseaseCase", params = {"name"})
    public TestResponse queryDiseaseCaseByName(@RequestParam("name") String name) {
        DiseaseCaseEntity diseaseCaseEntity =
                diseaseCaseTableDao.queryDiseaseCaseByName(name);

        TestResponse response = new TestResponse();
        if (diseaseCaseEntity != null) {
            response.setMessage(gson.toJson(diseaseCaseEntity));
        } else {
            response.setMessage("null");
        }

        return response;
    }
}