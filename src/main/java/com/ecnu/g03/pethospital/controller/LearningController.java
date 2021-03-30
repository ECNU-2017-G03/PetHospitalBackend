package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.enduser.response.learning.MultiDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.MultiDiseaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.SingleDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.SingleDiseaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.service.LearningService;
import com.ecnu.g03.pethospital.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class LearningController {
    private final LearningService learningService;

    @Autowired
    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping(value = "/user/learning/listDisease", params = {"size"})
    @JwtToken
    public ResponseEntity<?> listDisease(@RequestParam("size") int size) {
        List<DiseaseEntity> diseaseEntities = learningService.queryAllDiseases(size);
        if (diseaseEntities == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            MultiDiseaseResponse response = new MultiDiseaseResponse(diseaseEntities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/user/learning/queryDisease", params = {"id"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseById(@RequestParam("id") String id) {
        DiseaseEntity diseaseEntity = learningService.queryDiseaseById(id);
        if (diseaseEntity == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            SingleDiseaseResponse response = new SingleDiseaseResponse(diseaseEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/user/learning/queryDisease", params = {"name"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseByName(@RequestParam("name") String name) {
        DiseaseEntity diseaseEntity = learningService.queryDiseaseByName(name);
        if (diseaseEntity == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            SingleDiseaseResponse response = new SingleDiseaseResponse(diseaseEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/user/learning/listDiseaseCase", params = {"size"})
    @JwtToken
    public ResponseEntity<?> listDiseaseCase(@RequestParam("size") int size) {
        List<DiseaseCaseEntity> diseaseCaseEntities
                = learningService.queryAllDiseaseCases(size);
        if (diseaseCaseEntities == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            MultiDiseaseCaseResponse response = new MultiDiseaseCaseResponse(diseaseCaseEntities);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/user/learning/queryDiseaseCase", params = {"id"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseCaseById(@RequestParam("id") String id) {
        DiseaseCaseEntity diseaseCaseEntity = learningService.queryDiseaseCaseById(id);
        if (diseaseCaseEntity == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            SingleDiseaseCaseResponse response = new SingleDiseaseCaseResponse(diseaseCaseEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/user/learning/queryDiseaseCase", params = {"name"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseCaseByName(@RequestParam("name") String name) {
        DiseaseCaseEntity diseaseCaseEntity = learningService.queryDiseaseCaseByPetName(name);
        if (diseaseCaseEntity == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            SingleDiseaseCaseResponse response = new SingleDiseaseCaseResponse(diseaseCaseEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}