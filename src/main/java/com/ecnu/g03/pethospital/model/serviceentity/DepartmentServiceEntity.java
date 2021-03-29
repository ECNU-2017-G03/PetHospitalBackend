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
public class DepartmentServiceEntity extends TableServiceEntity {

    public DepartmentServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public DepartmentServiceEntity() {
        // Empty constructor for reflection
    }

    private String name;
    private String description;
}