package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;

import com.microsoft.azure.storage.table.*;
import org.springframework.stereotype.Repository;

/**
 * @author Juntao Peng, Jiayi Zhu
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
            TableServiceEntity tableServiceEntity = new TableServiceEntity(id, id);
            tableServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(tableServiceEntity));
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
        try {
            String filter = TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id);
            TableQuery<UserServiceEntity> rangeQuery = TableQuery.from(UserServiceEntity.class).where(filter);

            for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
                return UserEntity.fromServiceEntity(userServiceEntity);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UserEntity queryUserByName(String name) {
        try {
            TableQuery<UserServiceEntity> rangeQuery = TableQuery
                    .from(UserServiceEntity.class)
                    .where(
                        TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name)
                    );

            for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
                return UserEntity.fromServiceEntity(userServiceEntity);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UserEntity queryUserByNameAndPassword(String name, String password) {
        try {
            String nameFilter = TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name);
            String passwordFilter = TableQuery.generateFilterCondition("Password", TableQuery.QueryComparisons.EQUAL, password);
            String combinedFilter = TableQuery.combineFilters(nameFilter, TableQuery.Operators.AND, passwordFilter);
            TableQuery<UserServiceEntity> rangeQuery = TableQuery.from(UserServiceEntity.class).where(combinedFilter);

            for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
                return UserEntity.fromServiceEntity(userServiceEntity);
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
