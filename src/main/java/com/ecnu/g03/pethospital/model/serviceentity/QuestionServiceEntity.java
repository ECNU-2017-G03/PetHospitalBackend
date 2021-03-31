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
public class QuestionServiceEntity extends TableServiceEntity {

    public QuestionServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public QuestionServiceEntity() {
        // Empty constructor for reflection
    }

    private String answer;
    private String content;
    private String disease;
    private String option;
    private int score;
}