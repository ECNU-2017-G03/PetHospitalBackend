package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/22 9:53
 */
@Getter
@Setter
public class TestRecordServiceEntity extends TableServiceEntity {

    public TestRecordServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public TestRecordServiceEntity() {
        // Empty constructor for reflection
    }

    private String sid;
    private String quizId;
    private String pid;
    private int score;
}