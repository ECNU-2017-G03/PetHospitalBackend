package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/22 9:50
 */
@Getter
@Setter
public class ToolServiceEntity extends TableServiceEntity {

    public ToolServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public ToolServiceEntity() {
        // Blank constructor for reflection
    }

    private String name;
    private String description;
    private String picture;
}