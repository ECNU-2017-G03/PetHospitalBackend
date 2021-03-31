package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/22 10:05
 */
@Getter
@Setter
@NoArgsConstructor
public class DepartmentServiceEntity extends TableServiceEntity {
    private String name;
    private String vetDetail;
    private String nurseDetail;

    public DepartmentServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }
}