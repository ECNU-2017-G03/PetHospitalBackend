package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;

import com.microsoft.azure.storage.table.*;
import org.springframework.stereotype.Component;

/**
 * @author Juntao Peng, Jiayi Zhu
 * @date 2021/3/17 22:09
 */
@Component
public class TestPaperTableDao extends BaseTableDao {

    public TestPaperTableDao() {
        super("TestPaper");
    }

    public void queryTestPaperById(String id) {
        TableQuery<TestPaperServiceEntity> pkQuery = TableQuery
                .from(TestPaperServiceEntity.class)
                .where(
                    TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id)
                );

        for (TestPaperServiceEntity testPaperServiceEntity : cloudTable.execute(pkQuery)) {
            System.out.println(testPaperServiceEntity);
        }
    }
}
