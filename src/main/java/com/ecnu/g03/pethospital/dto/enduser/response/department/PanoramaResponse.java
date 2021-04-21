package com.ecnu.g03.pethospital.dto.enduser.response.department;

import com.ecnu.g03.pethospital.model.entity.TourEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Juntao Peng
 * @date Created in 2021/4/8 11:29
 */
@Data
@AllArgsConstructor
public class PanoramaResponse {
    private String id;
    private String departmentId;
    private String departmentName;
    private int x, y, z;

    public PanoramaResponse(TourEntity tourEntity) {
        this.id = tourEntity.getId();
        this.departmentId = tourEntity.getDepartmentId();
        this.departmentName = tourEntity.getDepartmentName();
        this.x = tourEntity.getX();
        this.y = tourEntity.getY();
        this.z = tourEntity.getZ();
    }
}
