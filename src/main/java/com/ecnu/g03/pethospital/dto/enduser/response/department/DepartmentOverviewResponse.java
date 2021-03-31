package com.ecnu.g03.pethospital.dto.enduser.response.department;

import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 23:20
 */
@Data
public class DepartmentOverviewResponse {
    List<DepartmentOverview> departmentList;

    public DepartmentOverviewResponse(List<DepartmentServiceEntity> departmentServiceEntity) {
        departmentList = new ArrayList<>();
        for (DepartmentServiceEntity department : departmentServiceEntity) {
            String id = department.getPartitionKey();
            String name = department.getName();
            DepartmentOverview departmentOverview = new DepartmentOverview(id, name);
            departmentList.add(departmentOverview);
        }
    }

    @Data
    @AllArgsConstructor
    static class DepartmentOverview {
        private String id;
        private String name;
    }
}
