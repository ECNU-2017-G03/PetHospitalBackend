package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.TourServiceEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Juntao Peng
 * @date 2021/4/8 11:33
 */
@Getter
@Setter
public class TourEntity extends BaseEntity {
    private String departmentId;
    private String departmentName;
    private int x, y, z;

    public TourEntity(String departmentId, String departmentName, int x, int y, int z) {
        super(UUID.randomUUID().toString());
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public TourEntity(String id, String departmentId, String departmentName, int x, int y, int z) {
        super(id);
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static TourEntity fromServiceEntity(TourServiceEntity tourServiceEntity) {
        TourEntity tourEntity = new TourEntity(
                tourServiceEntity.getPartitionKey(),
                tourServiceEntity.getDepartmentId(),
                tourServiceEntity.getDepartmentName(),
                tourServiceEntity.getX(),
                tourServiceEntity.getY(),
                tourServiceEntity.getZ()
        );
        return tourEntity;
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        TourServiceEntity tourServiceEntity = new TourServiceEntity(getId(), getId());
        tourServiceEntity.setDepartmentId(departmentId);
        tourServiceEntity.setDepartmentName(departmentName);
        tourServiceEntity.setX(x);
        tourServiceEntity.setY(y);
        tourServiceEntity.setZ(z);
        return tourServiceEntity;
    }
}
