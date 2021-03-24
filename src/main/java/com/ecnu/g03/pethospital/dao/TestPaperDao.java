package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.dto.response.FunctionalityLearningResponse;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:56
 */
@Component
public class TestPaperDao extends BaseTableDao{
    //private CloudTable testPaper;

    public TestPaperDao() {
            super("TestPaper");
    }

    public TestPaperEntity queryTestPaper(String id) {
        System.out.println(id);
        TableQuery<TestPaperServiceEntity> rangeQuery = TableQuery
                .from(TestPaperServiceEntity.class)
                .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
        Iterable<TestPaperServiceEntity> a = cloudTable.execute(rangeQuery);
        System.out.println(a);
        for(TestPaperServiceEntity testPaperServiceEntity: cloudTable.execute(rangeQuery)) {
            return TestPaperEntity.fromServiceEntity(testPaperServiceEntity);
        }
        return null;
    }

}
