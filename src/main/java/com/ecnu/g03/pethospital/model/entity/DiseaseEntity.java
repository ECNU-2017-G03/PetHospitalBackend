package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.DiseaseServiceEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Juntao Peng
 * @date 2021/3/17 22:09
 */
@Getter
@Setter
public class DiseaseEntity extends BaseEntity {
    private String name;
    private String description;

    public DiseaseEntity(String name, String description) {
        super(UUID.randomUUID().toString());
        this.name = name;
        this.description = description;
    }

    public DiseaseEntity(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public static DiseaseEntity fromServiceEntity(DiseaseServiceEntity diseaseServiceEntity) {
        return new DiseaseEntity(diseaseServiceEntity.getPartitionKey(), diseaseServiceEntity.getName(), diseaseServiceEntity.getDescription());
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        DiseaseServiceEntity diseaseServiceEntity = new DiseaseServiceEntity(getId(), getId());
        diseaseServiceEntity.setPartitionKey(getId());
        diseaseServiceEntity.setName(name);
        diseaseServiceEntity.setDescription(description);
        return diseaseServiceEntity;
    }
}
