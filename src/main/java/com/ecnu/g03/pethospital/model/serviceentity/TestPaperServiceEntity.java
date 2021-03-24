package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/22 9:57
 */
@Getter
@Setter
public class TestPaperServiceEntity extends TableServiceEntity {

    public TestPaperServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public TestPaperServiceEntity() {
        // Empty constructor for reflection
    }

    private String questions;
//    private String PartitionKey;
//    private String RowKey;
}