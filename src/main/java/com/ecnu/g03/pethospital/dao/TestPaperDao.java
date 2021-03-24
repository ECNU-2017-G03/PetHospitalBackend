package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:56
 */
public class TestPaperDao extends BaseTableDao{
    private static final String tableName = "testPaper";
    private CloudTable testPaper;

    public TestPaperDao() {
        try {
            testPaper = getTableClient().getTableReference(tableName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public TestPaperEntity queryTestPaper(String id) {
        TableQuery<TestPaperServiceEntity> rangeQuery = TableQuery
                .from(TestPaperServiceEntity.class)
                .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
        for(TestPaperServiceEntity testPaperServiceEntity: testPaper.execute(rangeQuery)) {
            return TestPaperEntity.fromServiceEntity(testPaperServiceEntity);
        }
        return null;
    }

}
