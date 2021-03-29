package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 22:04
 */
@Data
@NoArgsConstructor
public class AdminServiceEntity extends TableServiceEntity {

    public AdminServiceEntity(String partitionKey, String rowKey) {
        super(partitionKey, rowKey);
    }

    private String name;
    private String password;
    private String role;

}
