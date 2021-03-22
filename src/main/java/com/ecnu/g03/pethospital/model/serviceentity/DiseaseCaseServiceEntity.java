package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/22 10:05
 */
@Getter
@Setter
public class DiseaseCaseServiceEntity extends TableServiceEntity {

    public DiseaseCaseServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public DiseaseCaseServiceEntity() {
        // Empty constructor for reflection
    }

    private String name;
    private String description;
    private String petInfo;
    private String picture;
    private String video;
}