package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
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

    public List<String> queryAllDepartmentNames() {
        String[] columns = new String[]{"Name"};
        TableQuery<DepartmentServiceEntity> query = TableQuery.from(DepartmentServiceEntity.class).select(columns);

        List<String> departmentList = new ArrayList<>();
        for (DepartmentServiceEntity departmentServiceEntity : cloudTable.execute(query)) {
            departmentList.add(departmentServiceEntity.getName());
        }
        return departmentList;
    }

    public boolean insert(DepartmentEntity entity) {
        DepartmentServiceEntity departmentServiceEntity = (DepartmentServiceEntity) entity.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(departmentServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<DepartmentEntity> queryAll() {
        List<DepartmentEntity> result = new ArrayList<>();
        TableQuery<DepartmentServiceEntity> query = TableQuery
                .from(DepartmentServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(DepartmentEntity.fromServiceEntity(s))
        );
        return result;
    }

}
