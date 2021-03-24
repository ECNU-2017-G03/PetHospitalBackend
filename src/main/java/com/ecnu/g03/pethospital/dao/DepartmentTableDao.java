package com.ecnu.g03.pethospital.dao;

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

    public List<String> queryAllDepartmentNames() {
        try {
            String[] columns = new String[]{"Name"};
            TableQuery<DepartmentServiceEntity> query = TableQuery.from(DepartmentServiceEntity.class).select(columns);

            List<String> departmentList = new ArrayList<>();
            for (DepartmentServiceEntity departmentServiceEntity : cloudTable.execute(query)) {
                departmentList.add(departmentServiceEntity.getName());
            }
            return departmentList;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
