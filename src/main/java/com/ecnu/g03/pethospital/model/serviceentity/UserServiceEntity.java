package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juntao Peng
 * @date 2021/3/17 22:09
 */
@Getter
@Setter
public class UserServiceEntity extends TableServiceEntity {
    private String name;
    private String password;
    private String actor;

    public UserServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    public UserServiceEntity() {
        // Blank constructor for reflection
    }
}
