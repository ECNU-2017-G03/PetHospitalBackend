package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.parse.DepartmentDetail;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:38
 */
@Getter
@Setter
public class DepartmentEntity extends BaseEntity {

    private String name;
    private DepartmentDetail vetDetail;
    private DepartmentDetail nurseDetail;

    public DepartmentEntity(String id, String name, DepartmentDetail vetDetail, DepartmentDetail nurseDetail) {
        super(id);
        this.name = name;
        this.vetDetail = vetDetail;
        this.nurseDetail = nurseDetail;
    }

//    public DepartmentEntity(String name, String description) {
//        super(UUID.randomUUID().toString());
//        this.name = name;
//        this.description = description;
//    }

    public static DepartmentEntity fromServiceEntity(DepartmentServiceEntity departmentServiceEntity) {
        String id = departmentServiceEntity.getPartitionKey();
        String name = departmentServiceEntity.getName();
        String vetDetail = departmentServiceEntity.getVetDetail();
        String nurseDetail = departmentServiceEntity.getNurseDetail();
        DepartmentDetail vet = gson.fromJson(vetDetail, DepartmentDetail.class);
        DepartmentDetail nurse = gson.fromJson(nurseDetail, DepartmentDetail.class);
        return new DepartmentEntity(id, name, vet, nurse);
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        String id = getId();
        DepartmentServiceEntity departmentServiceEntity = new DepartmentServiceEntity(id, id);
        departmentServiceEntity.setName(name);
        departmentServiceEntity.setVetDetail(gson.toJson(vetDetail));
        departmentServiceEntity.setNurseDetail(gson.toJson(nurseDetail));
        return departmentServiceEntity;
    }
}
