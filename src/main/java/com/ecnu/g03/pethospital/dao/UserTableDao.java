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

    public void insertUser(UserEntity userEntity) {
        UserServiceEntity userServiceEntity = (UserServiceEntity) userEntity.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(userServiceEntity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUserById(String id) {
        try {
            TableServiceEntity tableServiceEntity = new TableServiceEntity(id, id);
            tableServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(tableServiceEntity));
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

        for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
            return UserEntity.fromServiceEntity(userServiceEntity);
        }
        return null;
    }

    public UserEntity queryUserByNameAndPassword(String name, String password) {
        String nameFilter = TableQuery.generateFilterCondition("Name", TableQuery.QueryComparisons.EQUAL, name);
        String passwordFilter = TableQuery.generateFilterCondition("Password", TableQuery.QueryComparisons.EQUAL, password);
        String combinedFilter = TableQuery.combineFilters(nameFilter, TableQuery.Operators.AND, passwordFilter);
        TableQuery<UserServiceEntity> rangeQuery = TableQuery.from(UserServiceEntity.class).where(combinedFilter);

        for (UserServiceEntity userServiceEntity : cloudTable.execute(rangeQuery)) {
            return UserEntity.fromServiceEntity(userServiceEntity);
        }
        return null;
    }
}
