package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Juntao Peng, Jiayi Zhu
 * @date Created in 2021/3/22 9:50
 */
@Getter
@Setter
@NoArgsConstructor
public class ToolServiceEntity extends TableServiceEntity {
    private String name;
    private String description;
    private String picture;

    public ToolServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }
}