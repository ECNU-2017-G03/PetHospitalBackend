package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/22 10:00
 */
@Getter
@Setter
public class QuizServiceEntity extends TableServiceEntity {

    public QuizServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public QuizServiceEntity() {
        // Empty constructor for reflection
    }

    private String startTime;
    private String endTime;
    private String pid;
    private String students;
}