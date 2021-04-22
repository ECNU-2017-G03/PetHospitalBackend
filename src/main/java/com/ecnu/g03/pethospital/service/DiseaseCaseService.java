package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.DiseaseCaseTableDao;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Shen Lei, Xueying Li
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

    public DiseaseCaseEntity insert(String name, String disease, String description, String petInfo, String picture, String video) {
        Gson gson = new Gson();
        DiseaseCaseEntity diseaseCaseEntity = new DiseaseCaseEntity(UUID.randomUUID().toString(), name, description, petInfo, video);
        diseaseCaseEntity.setDisease(gson.fromJson(disease, new TypeToken<List<String>>(){}.getType()));
        diseaseCaseEntity.setPicture(gson.fromJson(picture, new TypeToken<List<String >>(){}.getType()));
        if (!diseaseCaseTableDao.insert(diseaseCaseEntity)) {
            return null;
        }
        return diseaseCaseEntity;
    }

}
