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
public class AdminServiceEntity extends TableServiceEntity {

    public AdminServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public AdminServiceEntity() {
        // Empty constructor for reflection
    }

    private String name;
    private String role;
    private String password;
}