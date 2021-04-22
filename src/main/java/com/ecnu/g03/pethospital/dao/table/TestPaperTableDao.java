package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:56
 */
@Component
public class TestPaperTableDao extends BaseTableDao{
    public TestPaperTableDao() {
            super("TestPaper");
    }

    public TestPaperEntity queryTestPaper(String id) {
        TableQuery<TestPaperServiceEntity> rangeQuery = TableQuery
                .from(TestPaperServiceEntity.class)
                .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
        for(TestPaperServiceEntity testPaperServiceEntity: cloudTable.execute(rangeQuery)) {
            return TestPaperEntity.fromServiceEntity(testPaperServiceEntity);
        }
        return null;
    }

}
