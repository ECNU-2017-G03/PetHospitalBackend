package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.DiseaseTableDao;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:02
 */
@Service
public class DiseaseService {

    private final DiseaseTableDao diseaseTableDao;

    DiseaseService(DiseaseTableDao diseaseTableDao) {
        this.diseaseTableDao = diseaseTableDao;
    }

    public List<DiseaseEntity> getAll() {
        return diseaseTableDao.queryDiseases(100);
    }

    public boolean deleteById(String id) {
        return diseaseTableDao.deleteById(id);
    }

    public DiseaseEntity insert(String desc, String name) {
        DiseaseEntity diseaseEntity = new DiseaseEntity(name, desc);
        if (!diseaseTableDao.insert(diseaseEntity)) {
            return null;
        }
        return diseaseEntity;
    }

    public List<DiseaseEntity> searchById(String id) {
        List<DiseaseEntity> diseases = new ArrayList<>();
        DiseaseEntity disease = diseaseTableDao.queryDiseaseById(id);
        if (disease != null) {
            diseases.add(disease);
        }
        return diseases;
    }
}
