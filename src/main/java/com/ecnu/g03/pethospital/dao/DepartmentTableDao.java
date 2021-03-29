package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:49
 */
@Repository
public class DepartmentTableDao extends BaseTableDao {

    public DepartmentTableDao() {
        super("Department");
    }

    public List<DepartmentServiceEntity> queryAllDepartmentNameAndId() {
        String[] columns = new String[]{"PartitionKey", "Name"};
        TableQuery<DepartmentServiceEntity> query = TableQuery.from(DepartmentServiceEntity.class).select(columns);

        List<DepartmentServiceEntity> departmentList = new ArrayList<>();
        for (DepartmentServiceEntity departmentServiceEntity : cloudTable.execute(query)) {
            departmentList.add(departmentServiceEntity);
        }
        return departmentList;
    }

    public DepartmentEntity queryDepartmentDetailByIdAndActor(String id, String actor) throws Exception {
        String column;
        if (actor.equals("vet")) {
            column = "VetDetail";
        } else if (actor.equals("nurse")) {
            column = "NurseDetail";
        } else {
            throw new Exception("Invalid actor");
        }
        String[] columns = new String[]{column, "Name"};
        String filter = TableQuery.generateFilterCondition(
                "PartitionKey",
                TableQuery.QueryComparisons.EQUAL,
                id);
        TableQuery<DepartmentServiceEntity> query = TableQuery
                .from(DepartmentServiceEntity.class)
                .select(columns)
                .where(filter);

        for (DepartmentServiceEntity departmentServiceEntity : cloudTable.execute(query)) {
            return DepartmentEntity.fromServiceEntity(departmentServiceEntity);
        }
        return null;
    }

}
