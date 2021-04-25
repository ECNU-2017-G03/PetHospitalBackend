package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.dao.table.util.TableDaoUtils;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DiseaseServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
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

    public boolean deleteById(String id) {
        try {
            AdminServiceEntity adminServiceEntity = new AdminServiceEntity(id, id);
            adminServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(adminServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public AdminEntity queryByName(String name) {
        TableQuery<AdminServiceEntity> rangeQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                    TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name)
                );
        Iterable<AdminServiceEntity> result = cloudTable.execute(rangeQuery);
        if (!result.iterator().hasNext()) {
            return null;
        }
        return AdminEntity.fromServiceEntity(result.iterator().next());
    }

    public AdminEntity queryById(String id) {
        TableQuery<AdminServiceEntity> rangeQuery = TableQuery
                .from(AdminServiceEntity.class)
                .where(
                    TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id)
                );
        Iterable<AdminServiceEntity> result = cloudTable.execute(rangeQuery);
        System.out.print(result);
        AdminEntity a= AdminEntity.fromServiceEntity(result.iterator().next());
        System.out.print(a.getName());
        return a;
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
        if (!result.iterator().hasNext()) {
            return null;
        }
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

    public AdminEntity update(AdminEntity admin) {
        try {
            AdminServiceEntity adminServiceEntity = (AdminServiceEntity) admin.toServiceEntity();
            adminServiceEntity.setEtag("*");
            TableOperation operation = TableOperation.merge(adminServiceEntity);
            cloudTable.execute(operation);
            return admin;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<AdminEntity> queryByNameOrIdVague(String keyword) {
        List<AdminEntity> result = new ArrayList<>();
        try {
            TableQuery<AdminServiceEntity> query = TableQuery
                    .from(AdminServiceEntity.class)
                    .where(TableQuery.combineFilters(TableDaoUtils.containsPrefix("Name", keyword),
                            TableQuery.Operators.OR,
                            TableDaoUtils.containsPrefix("PartitionKey", keyword))
                    );
            Iterable<AdminServiceEntity> itResult = cloudTable.execute(query);
            itResult.forEach(r->result.add(AdminEntity.fromServiceEntity(r)));
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
    }

}
