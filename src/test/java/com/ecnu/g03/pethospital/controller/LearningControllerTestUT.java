package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.dto.response.learning.MultiDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.response.learning.MultiDiseaseResponse;
import com.ecnu.g03.pethospital.dto.response.learning.SingleDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.response.learning.SingleDiseaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.service.LearningService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LearningControllerTestUT {
    private final LearningService learningService = mock(LearningService.class);
    private final DiseaseEntity diseaseEntity = new DiseaseEntity("disease1", "diseaseDescription1");
    private final DiseaseEntity[] diseaseEntities = new DiseaseEntity[] {
            new DiseaseEntity("disease1", "diseaseDescription1"),
            new DiseaseEntity("disease2", "diseaseDescription2")
    };
    private final DiseaseCaseEntity diseaseCaseEntity =
            new DiseaseCaseEntity(
                    "case1",
                    "name1",
                    "info1",
                    Arrays.asList("pic1", "pic2"),
                    "vid1"
            );
    private final DiseaseCaseEntity[] diseaseCaseEntities =
            new DiseaseCaseEntity[] {
                    new DiseaseCaseEntity(
                            "case1",
                            "name1",
                            "info1",
                            Arrays.asList("pic1", "pic2"),
                            "vid1"
                    ),
                    new DiseaseCaseEntity(
                            "case2",
                            "name2",
                            "info2",
                            Arrays.asList("pic3", "pic4"),
                            "vid2"
                    )
            };
    
    @BeforeEach
    public void init() {
        
    }

    @AfterEach
    public void tearDown() {
        
    }

    @Test
    public void testQueryAllDiseasesRequestOK() {
        when(learningService.queryAllDiseases(2)).thenReturn(Arrays.asList(diseaseEntities));

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response = learningController.listDisease(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(
                diseaseEntities,
                ((MultiDiseaseResponse) Objects.requireNonNull(response.getBody())).getDiseaseEntityList().toArray()
        );
    }

    @Test
    public void testQueryAllDiseasesRequestServiceUnavailable() {
        when(learningService.queryAllDiseases(2)).thenReturn(null);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response = learningController.listDisease(2);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    public void testQueryDiseaseByIdRequestOK() {
        String id = diseaseEntity.getId();
        when(learningService.queryDiseaseById(id)).thenReturn(diseaseEntity);
        
        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseById(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                id,
                ((SingleDiseaseResponse) Objects.requireNonNull(response.getBody())).getDiseaseEntity().getId()
        );
    }
    
    @Test
    public void testQueryDiseaseByIdRequestServiceUnavailable() {
        String id = diseaseEntity.getId();
        when(learningService.queryDiseaseById(id)).thenReturn(null);
        
        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseById(id);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    public void testQueryDiseaseByNameRequestOK() {
        String name = diseaseEntity.getName();
        when(learningService.queryDiseaseByName(name)).thenReturn(diseaseEntity);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                name,
                ((SingleDiseaseResponse) Objects.requireNonNull(response.getBody())).getDiseaseEntity().getName()
        );
    }

    @Test
    public void testQueryDiseaseByNameRequestServiceUnavailable() {
        String name = diseaseEntity.getName();
        when(learningService.queryDiseaseByName(name)).thenReturn(null);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseByName(name);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    public void testQueryAllDiseaseCasesRequestOK() {
        when(learningService.queryAllDiseaseCases(2)).thenReturn(Arrays.asList(diseaseCaseEntities));

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response = learningController.listDiseaseCase(2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(
                diseaseCaseEntities,
                ((MultiDiseaseCaseResponse) Objects.requireNonNull(response.getBody())).getDiseaseCaseEntityList().toArray()
        );
    }

    @Test
    public void testQueryAllDiseaseCasesRequestServiceUnavailable() {
        when(learningService.queryAllDiseaseCases(2)).thenReturn(null);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response = learningController.listDiseaseCase(2);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    public void testQueryDiseaseCaseByIdRequestOK() {
        String id = diseaseCaseEntity.getId();
        when(learningService.queryDiseaseCaseById(id)).thenReturn(diseaseCaseEntity);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseCaseById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                id,
                ((SingleDiseaseCaseResponse) Objects.requireNonNull(response.getBody())).getDiseaseCaseEntity().getId()
        );
    }

    @Test
    public void testQueryDiseaseCaseByIdRequestServiceUnavailable() {
        String id = diseaseCaseEntity.getId();
        when(learningService.queryDiseaseCaseById(id)).thenReturn(null);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseCaseById(id);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }

    @Test
    public void testQueryDiseaseCaseByNameRequestOK() {
        String name = diseaseCaseEntity.getName();
        when(learningService.queryDiseaseCaseByPetName(name)).thenReturn(diseaseCaseEntity);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseCaseByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                name,
                ((SingleDiseaseCaseResponse) Objects.requireNonNull(response.getBody())).getDiseaseCaseEntity().getName()
        );
    }

    @Test
    public void testQueryDiseaseCaseByNameRequestServiceUnavailable() {
        String name = diseaseCaseEntity.getName();
        when(learningService.queryDiseaseCaseByPetName(name)).thenReturn(null);

        LearningController learningController = new LearningController(learningService);
        ResponseEntity<?> response =
                learningController.queryDiseaseCaseByName(name);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    }
}