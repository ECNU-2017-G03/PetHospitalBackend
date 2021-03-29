package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;
import com.ecnu.g03.pethospital.service.AdminService;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableServiceEntity;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @Author Shen Lei
 * @Date 2021/3/28 21:31
 */
@Repository
public class AdminTableDao extends BaseTableDao {

    private static final String tableName = "Admin";
    private CloudTable adminTable;

    public AdminTableDao() {
        try {
            adminTable = getTableClient().getTableReference(tableName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insert(AdminEntity adminEntity) {
        AdminServiceEntity adminServiceEntity = (AdminServiceEntity) adminEntity.toServiceEntity();
        try {
            adminTable.execute(TableOperation.insert(adminServiceEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(String id) {
        try {
            AdminServiceEntity adminServiceEntity = new AdminServiceEntity(id, id);
            adminServiceEntity.setEtag("*");
            adminTable.execute(TableOperation.delete(adminServiceEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<AdminEntity> queryByName(String name) {
        List<AdminEntity> result = new ArrayList<>();
        TableQuery<AdminServiceEntity> rangeQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                    TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name)
                );
        for (AdminServiceEntity adminServiceEntity : adminTable.execute(rangeQuery)) {
            result.add(AdminEntity.fromServiceEntity(adminServiceEntity));
        }
        return result;
    }

    public AdminEntity queryByNameAndPassword(String name, String password) {
        AdminEntity result = null;
        TableQuery<AdminServiceEntity> pointQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                        TableQuery.combineFilters(
                                TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name),
                                TableQuery.Operators.AND,
                                TableQuery.generateFilterCondition("Password", TableQuery.QueryComparisons.EQUAL, password)
                        )
                );
        result = AdminEntity.fromServiceEntity(adminTable.execute(pointQuery).iterator().next());
        return result;
    }

    public List<AdminEntity> queryAll() {
        List<AdminEntity> result = new ArrayList<>();
        TableQuery<AdminServiceEntity> query = TableQuery
                .from(AdminServiceEntity.class);
        adminTable.execute(query).forEach(
                (as) -> result.add(AdminEntity.fromServiceEntity(as))
        );
        return result;
    }

}
