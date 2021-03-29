package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.DiseaseCaseServiceEntity;
import com.google.gson.reflect.TypeToken;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * @author Juntao Peng
 * @date 2021/3/17 22:09
 */
@Getter
@Setter
public class DiseaseCaseEntity extends BaseEntity {
    private String name;
    private List<String> disease;
    private String description;
    private String petInfo;
    private List<String> picture;
    private String video;

    public DiseaseCaseEntity(String name, List<String> disease, String description, String petInfo, List<String> picture, String video) {
        super(UUID.randomUUID().toString());
        this.name = name;
        this.disease = disease;
        this.description = description;
        this.petInfo = petInfo;
        this.picture = picture;
        this.video = video;
    }

    public DiseaseCaseEntity(String id, String name, String description, String petInfo, String video) {
        super(id);
        this.name = name;
        this.description = description;
        this.petInfo = petInfo;
        this.video = video;
    }

    public static DiseaseCaseEntity fromServiceEntity(DiseaseCaseServiceEntity diseaseCaseServiceEntity) {
        DiseaseCaseEntity diseaseCaseEntity = new DiseaseCaseEntity(
                diseaseCaseServiceEntity.getPartitionKey(),
                diseaseCaseServiceEntity.getName(),
                diseaseCaseServiceEntity.getDescription(),
                diseaseCaseServiceEntity.getPetInfo(),
                diseaseCaseServiceEntity.getVideo()
        );
        String pictureJsonString = diseaseCaseServiceEntity.getPicture();
        List<String> picture = gson.fromJson(pictureJsonString, new TypeToken<List<String>>(){}.getType());
        diseaseCaseEntity.setPicture(picture);
        String diseaseJsonString = diseaseCaseServiceEntity.getDisease();
        List<String> disease = gson.fromJson(diseaseJsonString, new TypeToken<List<String>>(){}.getType());
        diseaseCaseEntity.setDisease(disease);
        return diseaseCaseEntity;
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        DiseaseCaseServiceEntity diseaseCaseServiceEntity = new DiseaseCaseServiceEntity(getId(), getId());
        diseaseCaseServiceEntity.setPartitionKey(getId());
        diseaseCaseServiceEntity.setName(name);
        diseaseCaseServiceEntity.setDescription(description);
        return diseaseCaseServiceEntity;
    }
}
