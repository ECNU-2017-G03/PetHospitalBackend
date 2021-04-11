package com.ecnu.g03.pethospital.dto.enduser.response.department;

import com.ecnu.g03.pethospital.model.entity.TourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng
 * @date Created in 2021/4/8 11:40
 */
@Data
public class TourResponse {
    List<DepartmentTour> departmentTourList;

    public TourResponse(List<TourEntity> tourEntityList) {
        this.departmentTourList = new ArrayList<>();
        for (TourEntity tourEntity : tourEntityList) {
            departmentTourList.add(new DepartmentTour(tourEntity.getDepartmentId(), tourEntity.getDepartmentName()));
        }
    }

    @Data
    @AllArgsConstructor
    static class DepartmentTour {
        private String departmentId;
        private String departmentName;
    }
}
