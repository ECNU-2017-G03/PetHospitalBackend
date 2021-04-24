package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Data;

/**
 * @author Yiqing Tao
 * @date Created in 2021/3/24 19:52
 */
@Data
public class TestResultServiceEntity extends TableServiceEntity {
    public TestResultServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public TestResultServiceEntity() {
        // Empty constructor for reflection
    }

    private String pid;
    private String sid;
    private String QAnswer;
}
