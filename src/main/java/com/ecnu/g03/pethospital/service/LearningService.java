package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.DiseaseCaseTableDao;
import com.ecnu.g03.pethospital.dao.DiseaseTableDao;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @return A list of DiseaseEntity
     */
    public List<DiseaseEntity> queryAllDiseases(int size) {
        return diseaseTableDao.queryDiseases(size);
    }

    /**
     * Get a disease by unique id
     * @param id The unique id
     * @return A DiseaseEntity with the given id
     *         null otherwise
     */
    public DiseaseEntity queryDiseaseById(String id) {
        return diseaseTableDao.queryDiseaseById(id);
    }

    /**
     * Get a disease by its name
     * @param name The disease name
     * @return A DiseaseEntity with the given name
     *         null otherwise
     */
    public DiseaseEntity queryDiseaseByName(String name) {
        return diseaseTableDao.queryDiseaseByName(name);
    }

    /**
     * Get a list of all disease cases
     * @param size Max size of the disease case list
     * @return A list of DiseaseCaseEntity
     */
    public List<DiseaseCaseEntity> queryAllDiseaseCases(int size) {
        return diseaseCaseTableDao.queryDiseaseCases(size);
    }

    /**
     * Get a disease case by unique id
     * @param id The unique id
     * @return A DiseaseCaseEntity with the given id
     *         null otherwise
     */
    public DiseaseCaseEntity queryDiseaseCaseById(String id) {
        return diseaseCaseTableDao.queryDiseaseCaseById(id);
    }

    /**
     * Get a disease case by its pet name
     * @param petName The pet name
     * @return A DiseaseEntity with the given pet name
     *         null otherwise
     */
    public DiseaseCaseEntity queryDiseaseCaseByPetName(String petName) {
        return diseaseCaseTableDao.queryDiseaseCaseByName(petName);
    }
}