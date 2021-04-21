package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.TourTableDao;
import com.ecnu.g03.pethospital.dto.enduser.response.department.PanoramaResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.department.TourResponse;
import com.ecnu.g03.pethospital.model.entity.TourEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/4/8 11:24
 */
@Service
public class TourService {
    private final TourTableDao tourTableDao;

    @Autowired
    public TourService(TourTableDao tourTableDao) {
        this.tourTableDao = tourTableDao;
    }

    public TourResponse listTours(int size) {
        List<TourEntity> tourEntityList = tourTableDao.listTours(size);
        if (tourEntityList == null) {
            return null;
        }
        return new TourResponse(tourEntityList);
    }

    public PanoramaResponse queryPanoramaByDepartmentId(String departmentId) {
        TourEntity tourEntity = tourTableDao.queryTourByDepartmentId(departmentId);
        if (tourEntity == null) {
            return null;
        }
        return new PanoramaResponse(tourEntity);
    }
}
