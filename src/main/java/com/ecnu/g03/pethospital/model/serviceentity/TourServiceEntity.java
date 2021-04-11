package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date Created in 2021/4/8 11:29
 */
@Getter
@Setter
public class TourServiceEntity extends TableServiceEntity {
    private String departmentId;
    private String departmentName;
    private int x, y, z;

    public TourServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public TourServiceEntity() {
        // Blank constructor for reflection
    }
}
