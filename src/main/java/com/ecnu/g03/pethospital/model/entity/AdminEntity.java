package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Data;

import java.util.UUID;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 21:47
 */
@Data
public class AdminEntity extends BaseEntity {

    private String name;
    private String password;
    private AdminRole role;

    public AdminEntity() {
        super(UUID.randomUUID().toString());
    }

    public AdminEntity(String name, String password, AdminRole role) {
        super(UUID.randomUUID().toString());
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public AdminEntity(String id, String name, String password, AdminRole role) {
        super(id);
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public static AdminEntity fromServiceEntity(AdminServiceEntity adminServiceEntity) {
        System.out.println("from service entity: name " + adminServiceEntity.getName());
        System.out.println("from service entity: password " + adminServiceEntity.getPassword());
        System.out.println("from service entity: role " + adminServiceEntity.getRole());
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(adminServiceEntity.getPartitionKey());
        adminEntity.setName(adminServiceEntity.getName());
        adminEntity.setPassword(adminServiceEntity.getPassword());
        adminEntity.setRole(AdminRole.fromString(adminServiceEntity.getRole()));
        return adminEntity;
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        AdminServiceEntity adminServiceEntity = new AdminServiceEntity(super.getId(), super.getId());
        adminServiceEntity.setName(this.name);
        adminServiceEntity.setPassword(this.password);
        adminServiceEntity.setRole(this.role.toString());
        return adminServiceEntity;
    }

}
