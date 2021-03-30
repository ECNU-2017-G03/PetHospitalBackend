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
public class DiseaseServiceEntity extends TableServiceEntity {

    public DiseaseServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public DiseaseServiceEntity() {
        // Empty constructor for reflection
    }

    public DiseaseServiceEntity(String partitionKey, String rowKey, String name, String description) {
        super(partitionKey, rowKey);
        this.name = name;
        this.description = description;
    }

    private String name;
    private String description;
}