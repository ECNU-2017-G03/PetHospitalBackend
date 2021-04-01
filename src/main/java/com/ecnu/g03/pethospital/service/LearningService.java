package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.DiseaseCaseTableDao;
import com.ecnu.g03.pethospital.dao.DiseaseTableDao;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.MultiDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.MultiDiseaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.SingleDiseaseCaseResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.learning.SingleDiseaseResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/25 9:48
 */
@Service
public class LearningService {
    private final DiseaseTableDao diseaseTableDao;
    private final DiseaseCaseTableDao diseaseCaseTableDao;

    @Autowired
    public LearningService(
            DiseaseTableDao diseaseTableDao,
            DiseaseCaseTableDao diseaseCaseTableDao
    ) {
        this.diseaseTableDao = diseaseTableDao;
        this.diseaseCaseTableDao = diseaseCaseTableDao;
    }

    /**
     * Get a list of all diseases
     * @param size Max size of the disease list
     * @return A MultiDiseaseResponse, null otherwise
     */
    public MultiDiseaseResponse queryAllDiseases(int size) {
        List<DiseaseEntity> diseaseEntityList = diseaseTableDao.queryDiseases(size);
        if (diseaseEntityList == null) {
            return null;
        }
        return new MultiDiseaseResponse(diseaseEntityList);
    }

    /**
     * Get a disease by unique id
     * @param id The unique id
     * @return A SingleDiseaseResponse, null otherwise
     */
    public SingleDiseaseResponse queryDiseaseById(String id) {
        DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseById(id);
        if (diseaseEntity == null) {
            return null;
        }
        return new SingleDiseaseResponse(diseaseEntity);
    }

    /**
     * Get a disease by its name
     * @param name The disease name
     * @return A SingleDiseaseResponse
     *         null otherwise
     */
    public SingleDiseaseResponse queryDiseaseByName(String name) {
        DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseByName(name);
        if (diseaseEntity == null) {
            return null;
        }
        return new SingleDiseaseResponse(diseaseEntity);
    }

    /**
     * Get a list of all disease cases
     * @param size Max size of the disease case list
     * @return A MultiDiseaseCaseResponse, null otherwise
     */
    public MultiDiseaseCaseResponse queryAllDiseaseCases(int size) {
        List<DiseaseCaseEntity> diseaseCaseEntityList = diseaseCaseTableDao.queryDiseaseCases(size);
        if (diseaseCaseEntityList == null) {
            return null;
        }
        return new MultiDiseaseCaseResponse(diseaseCaseEntityList);
    }

    /**
     * Get a disease case by unique id
     * @param id The unique id
     * @return A DiseaseCaseEntity with the given id
     *         null otherwise
     */
    public SingleDiseaseCaseResponse queryDiseaseCaseById(String id) {
        DiseaseCaseEntity diseaseCaseEntity = diseaseCaseTableDao.queryDiseaseCaseById(id);
        if (diseaseCaseEntity == null) {
            return null;
        }
        List<String> diseaseNameList = new ArrayList<>();
        for (String diseaseId : diseaseCaseEntity.getDisease()) {
            DiseaseEntity diseaseEntity = diseaseTableDao.queryDiseaseById(diseaseId);
            if (diseaseEntity != null) {
                diseaseNameList.add(diseaseEntity.getName());
            }
        }
        return new SingleDiseaseCaseResponse(diseaseCaseEntity, diseaseNameList);
    }

    /**
     * Get a disease case by its pet name
     * @param petName The pet name
     * @return A DiseaseEntity with the given pet name
     *         null otherwise
     */
    public SingleDiseaseCaseResponse queryDiseaseCaseByPetName(String petName) {
        DiseaseCaseEntity diseaseCaseEntity = diseaseCaseTableDao.queryDiseaseCaseByName(petName);
        if (diseaseCaseEntity == null) {
            return null;
        }
        return new SingleDiseaseCaseResponse(diseaseCaseEntity, null);
    }
}