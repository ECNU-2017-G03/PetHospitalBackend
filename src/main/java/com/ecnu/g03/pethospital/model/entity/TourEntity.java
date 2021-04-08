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
    private String coverPicture;
    private String panoramaPicture;
    private int x, y;

    public TourEntity(String departmentId, String departmentName, String coverPicture, String panoramaPicture, int x, int y) {
        super(UUID.randomUUID().toString());
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.coverPicture = coverPicture;
        this.panoramaPicture = panoramaPicture;
        this.x = x;
        this.y = y;
    }

    public TourEntity(String id, String departmentId, String departmentName, String coverPicture, String panoramaPicture, int x, int y) {
        super(id);
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.coverPicture = coverPicture;
        this.panoramaPicture = panoramaPicture;
        this.x = x;
        this.y = y;
    }

    public static TourEntity fromServiceEntity(TourServiceEntity tourServiceEntity) {
        TourEntity tourEntity = new TourEntity(
                tourServiceEntity.getPartitionKey(),
                tourServiceEntity.getDepartmentId(),
                tourServiceEntity.getDepartmentName(),
                tourServiceEntity.getCoverPicture(),
                tourServiceEntity.getPanoramaPicture(),
                tourServiceEntity.getX(),
                tourServiceEntity.getY()
        );
        return tourEntity;
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        TourServiceEntity tourServiceEntity = new TourServiceEntity(getId(), getId());
        tourServiceEntity.setDepartmentId(departmentId);
        tourServiceEntity.setDepartmentName(departmentName);
        tourServiceEntity.setCoverPicture(coverPicture);
        tourServiceEntity.setPanoramaPicture(panoramaPicture);
        tourServiceEntity.setX(x);
        tourServiceEntity.setY(y);
        return tourServiceEntity;
    }
}
