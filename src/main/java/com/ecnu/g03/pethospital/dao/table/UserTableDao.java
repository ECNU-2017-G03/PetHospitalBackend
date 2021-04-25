package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.dao.table.util.TableDaoUtils;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juntao Peng, Jiayi Zhu, Shen Lei
 * @date 2021/3/17 22:09
 */
@Repository
public class UserTableDao extends BaseTableDao {

    public UserTableDao() {
        super("User");
    }

    public boolean insertUser(UserEntity userEntity) {
        UserServiceEntity userServiceEntity = (UserServiceEntity) userEntity.toServiceEntity();
        try {
            TableOperation operation = TableOperation.insert(userServiceEntity);
            cloudTable.execute(operation);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteUserById(String id) {
        try {
            UserServiceEntity userServiceEntity = new UserServiceEntity(id, id);
            userServiceEntity.setEtag("*");
            TableOperation operation = TableOperation.delete(userServiceEntity);
            cloudTable.execute(operation);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateUserPassword(String id, String password) {
        try {
            UserServiceEntity userServiceEntity = new UserServiceEntity(id, id);
            userServiceEntity.setEtag("*");
            userServiceEntity.setPassword(password);
            TableOperation operation = TableOperation.merge(userServiceEntity);
            cloudTable.execute(operation);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public UserEntity queryUserById(String id) {
        Iterable<UserServiceEntity> result = null;
        synchronized (this) {
            String filter = TableQuery.generateFilterCondition(
                    "PartitionKey",
                    TableQuery.QueryComparisons.EQUAL,
                    id);
            TableQuery<UserServiceEntity> rangeQuery = TableQuery.from(UserServiceEntity.class).where(filter);
            result = cloudTable.execute(rangeQuery);

        }
        for (UserServiceEntity userServiceEntity : result) {
            return UserEntity.fromServiceEntity(userServiceEntity);
        }
        return null;
    }

    public UserEntity queryUserByName(String name) {
        String filter = TableQuery.generateFilterCondition(
                "Name",
                TableQuery.QueryComparisons.EQUAL,
                name);
        TableQuery<UserServiceEntity> rangeQuery = TableQuery.from(UserServiceEntity.class).where(filter);

        for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
            return UserEntity.fromServiceEntity(userServiceEntity);
        }
        return null;
    }

    public UserEntity queryUserByNameAndPassword(String name, String password) {
        String nameFilter = TableQuery.generateFilterCondition(
                "Name",
                TableQuery.QueryComparisons.EQUAL,
                name);
        String passwordFilter = TableQuery.generateFilterCondition(
                "Password",
                TableQuery.QueryComparisons.EQUAL,
                password);
        String combinedFilter = TableQuery.combineFilters(nameFilter, TableQuery.Operators.AND, passwordFilter);
        TableQuery<UserServiceEntity> rangeQuery = TableQuery.from(UserServiceEntity.class).where(combinedFilter);

        for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
            return UserEntity.fromServiceEntity(userServiceEntity);
        }
        return null;
    }

    public UserEntity update(UserEntity user) {
        try {
            UserServiceEntity userServiceEntity = (UserServiceEntity) user.toServiceEntity();
            userServiceEntity.setEtag("*");
            TableOperation operation = TableOperation.merge(userServiceEntity);
            cloudTable.execute(operation);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<UserEntity> getAll() {
        List<UserEntity> result = new ArrayList<>();
        TableQuery<UserServiceEntity> query = TableQuery
                .from(UserServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(UserEntity.fromServiceEntity(s))
        );
        return result;
    }

    public List<UserEntity> queryByNameOrIdVague(String keyword) {
        List<UserEntity> result = new ArrayList<>();
        try {
            // query by name
            TableQuery<UserServiceEntity> nameQuery = TableQuery
                    .from(UserServiceEntity.class)
                    .where(TableQuery.combineFilters(TableDaoUtils.containsPrefix("Name", keyword),
                            TableQuery.Operators.OR,
                            TableDaoUtils.containsPrefix("PartitionKey", keyword))
                    );
            Iterable<UserServiceEntity> nameResult = cloudTable.execute(nameQuery);
            nameResult.forEach(r->result.add(UserEntity.fromServiceEntity(r)));
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
    }

}
