package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.dto.enduser.response.learning.MultiDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.MultiDiseaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.SingleDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.SingleDiseaseResponse;
import com.ecnu.g03.pethospital.service.LearningService;
import com.ecnu.g03.pethospital.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/24 13:11
 */
@RestController
@RequestMapping("/user/learning")
public class LearningController {
    private final LearningService learningService;

    @Autowired
    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping(value = "/listDisease", params = {"size"})
    @JwtToken
    public ResponseEntity<?> listDisease(@RequestParam("size") int size) {
        MultiDiseaseResponse response = learningService.queryAllDiseases(size);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/queryDisease", params = {"id"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseById(@RequestParam("id") String id) {
        SingleDiseaseResponse response = learningService.queryDiseaseById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/queryDisease", params = {"name"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseByName(@RequestParam("name") String name) {
        SingleDiseaseResponse response = learningService.queryDiseaseByName(name);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/listDiseaseCase", params = {"size"})
    @JwtToken
    public ResponseEntity<?> listDiseaseCase(@RequestParam("size") int size) {
        MultiDiseaseCaseResponse response = learningService.queryAllDiseaseCases(size);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/queryDiseaseCase", params = {"id"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseCaseById(@RequestParam("id") String id) {
        SingleDiseaseCaseResponse response = learningService.queryDiseaseCaseById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/queryDiseaseCase", params = {"name"})
    @JwtToken
    public ResponseEntity<?> queryDiseaseCaseByName(@RequestParam("name") String name) {
        SingleDiseaseCaseResponse response = learningService.queryDiseaseCaseByPetName(name);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}