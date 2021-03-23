package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.*;
import org.springframework.stereotype.Component;

/**
 * @author Juntao Peng
 * @date 2021/3/17 22:09
 */
@Component
public class UserTableDao extends BaseTableDao {
    private static final String tableName = "User";

    public void insertUser(UserEntity userEntity) {
        UserServiceEntity userServiceEntity = (UserServiceEntity) userEntity.toServiceEntity();
        try {
            CloudTable userTable = getTableClient().getTableReference(tableName);
            userTable.execute(TableOperation.insert(userServiceEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUserById(String id) {
        try {
            TableServiceEntity tableServiceEntity = new TableServiceEntity(id, id);
            tableServiceEntity.setEtag("*");
            CloudTable userTable = getTableClient().getTableReference(tableName);
            userTable.execute(TableOperation.delete(tableServiceEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UserEntity queryUserByName(String name) {
        TableQuery<UserServiceEntity> rangeQuery = TableQuery
                .from(UserServiceEntity.class)
                .where(
                    TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name)
                );

        try {
            CloudTable userTable = getTableClient().getTableReference(tableName);
            for (UserServiceEntity userServiceEntity : userTable.execute(rangeQuery)) {
                return UserEntity.fromServiceEntity(userServiceEntity);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
