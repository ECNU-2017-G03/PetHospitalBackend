package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.DiseaseCaseTableDao;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:36
 */
@Service
public class DiseaseCaseService {

    private final DiseaseCaseTableDao diseaseCaseTableDao;

    @Autowired
    DiseaseCaseService(DiseaseCaseTableDao diseaseCaseTableDao) {
        this.diseaseCaseTableDao = diseaseCaseTableDao;
    }

    public List<DiseaseCaseEntity> getAll() {
        return diseaseCaseTableDao.queryDiseaseCases(100);
    }

    public boolean deleteById(String id) {
        return diseaseCaseTableDao.deleteById(id);
    }

}
