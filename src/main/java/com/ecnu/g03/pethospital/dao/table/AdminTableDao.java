package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Shen Lei
 * @Date 2021/3/28 21:31
 */
@Repository
public class AdminTableDao extends BaseTableDao {

    public AdminTableDao() {
        super("Admin");
    }

    public boolean insert(AdminEntity adminEntity) {
        AdminServiceEntity adminServiceEntity = (AdminServiceEntity) adminEntity.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(adminServiceEntity));
            return true;
        } catch (StorageException e) {

            e.printStackTrace();
            return false;
        }
    }

    public void deleteById(String id) {
        try {
            AdminServiceEntity adminServiceEntity = new AdminServiceEntity(id, id);
            adminServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(adminServiceEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AdminEntity queryByName(String name) {
        TableQuery<AdminServiceEntity> rangeQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                    TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name)
                );
        Iterable<AdminServiceEntity> result = cloudTable.execute(rangeQuery);
        return AdminEntity.fromServiceEntity(result.iterator().next());
    }

    public AdminEntity queryById(String id) {
        TableQuery<AdminServiceEntity> rangeQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                        TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id)
                );
        Iterable<AdminServiceEntity> result = cloudTable.execute(rangeQuery);
        return AdminEntity.fromServiceEntity(result.iterator().next());
    }

    public AdminEntity queryByNameAndPassword(String name, String password) {
        TableQuery<AdminServiceEntity> pointQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                        TableQuery.combineFilters(
                                TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name),
                                TableQuery.Operators.AND,
                                TableQuery.generateFilterCondition("Password", TableQuery.QueryComparisons.EQUAL, password)
                        )
                );
        Iterable<AdminServiceEntity> result = cloudTable.execute(pointQuery);
        return AdminEntity.fromServiceEntity(result.iterator().next());
    }

    public List<AdminEntity> queryAll() {
        List<AdminEntity> result = new ArrayList<>();
        TableQuery<AdminServiceEntity> query = TableQuery
                .from(AdminServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(AdminEntity.fromServiceEntity(s))
        );
        return result;
    }

}