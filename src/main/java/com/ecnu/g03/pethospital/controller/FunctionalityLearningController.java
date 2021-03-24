package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dao.DiseaseCaseTableDao;
import com.ecnu.g03.pethospital.dao.DiseaseTableDao;
import com.ecnu.g03.pethospital.dao.TestPaperTableDao;
import com.ecnu.g03.pethospital.dto.response.FunctionalityLearningResponse;
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
    private DiseaseTableDao diseaseTableDao;
    private DiseaseCaseTableDao diseaseCaseTableDao;

    @Autowired
    private TestPaperTableDao testPaperTableDao;

    @Autowired
    public FunctionalityLearningController(
            DiseaseTableDao diseaseTableDao,
            DiseaseCaseTableDao diseaseCaseTableDao
    ) {
        this.diseaseTableDao = diseaseTableDao;
        this.diseaseCaseTableDao = diseaseCaseTableDao;
    }

    @GetMapping("/learning/listDisease")
    public FunctionalityLearningResponse listDisease() {
        List<DiseaseEntity> diseaseEntities = diseaseTableDao.queryDiseases(5);

        FunctionalityLearningResponse response = new FunctionalityLearningResponse();
        response.setDiseaseEntities(diseaseEntities);

        return response;
    }

    @GetMapping(value = "learning/queryDisease", params = {"id"})
    public FunctionalityLearningResponse queryDiseaseById(@RequestParam("id") String id) {
        DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseById(id);

        FunctionalityLearningResponse response = new FunctionalityLearningResponse();
        response.setDiseaseEntity(diseaseEntity);

        return response;
    }

    @GetMapping(value = "learning/queryDisease", params = {"name"})
    public FunctionalityLearningResponse queryDiseaseByName(@RequestParam("name") String name) {
        DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseByName(name);

        FunctionalityLearningResponse response = new FunctionalityLearningResponse();
        response.setDiseaseEntity(diseaseEntity);

        return response;
    }

    @GetMapping(value = "learning/listDiseaseCase")
    public FunctionalityLearningResponse listDiseaseCase() {
        List<DiseaseCaseEntity> diseaseCaseEntities
                = diseaseCaseTableDao.queryDiseaseCases(5);

        FunctionalityLearningResponse response = new FunctionalityLearningResponse();
        response.setDiseaseCaseEntities(diseaseCaseEntities);

        return response;
    }

    @GetMapping(value = "learning/queryDiseaseCase", params = {"id"})
    public FunctionalityLearningResponse queryDiseaseCaseById(@RequestParam("id") String id) {
        DiseaseCaseEntity diseaseCaseEntity = diseaseCaseTableDao.queryDiseaseCaseById(id);

        FunctionalityLearningResponse response = new FunctionalityLearningResponse();
        response.setDiseaseCaseEntity(diseaseCaseEntity);

        return response;
    }

    @GetMapping(value = "learning/queryDiseaseCase", params = {"name"})
    public FunctionalityLearningResponse queryDiseaseCaseByName(@RequestParam("name") String name) {
        DiseaseCaseEntity diseaseCaseEntity =
                diseaseCaseTableDao.queryDiseaseCaseByName(name);

        FunctionalityLearningResponse response = new FunctionalityLearningResponse();
        response.setDiseaseCaseEntity(diseaseCaseEntity);

        return response;
    }

    @GetMapping(value = "testPaper", params = {"id"})
    public FunctionalityLearningResponse queryTestPaperById(@RequestParam("id") String id) {
        testPaperTableDao.queryTestPaperById(id);
        return new FunctionalityLearningResponse();
    }
}