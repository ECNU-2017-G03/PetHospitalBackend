package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:38
 */
@Getter
@Setter
public class DepartmentEntity extends BaseEntity {
    private String name;
    private String description;

    public DepartmentEntity(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public static DepartmentEntity fromServiceEntity(DepartmentServiceEntity departmentServiceEntity) {
        String id = departmentServiceEntity.getPartitionKey();
        String name = departmentServiceEntity.getName();
        String description = departmentServiceEntity.getDescription();
        return new DepartmentEntity(id, name, description);
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        String id = getId();
        DepartmentServiceEntity departmentServiceEntity = new DepartmentServiceEntity(id, id);
        departmentServiceEntity.setName(name);
        departmentServiceEntity.setDescription(description);
        return departmentServiceEntity;
    }
}
