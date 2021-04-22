package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public boolean insert(String id, String questions) {
        TestPaperServiceEntity testPaperServiceEntity = new TestPaperServiceEntity(id, id);
        testPaperServiceEntity.setQuestions(questions);
        try {
            cloudTable.execute(TableOperation.insert(testPaperServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(String id) {
        try {
            TestPaperServiceEntity testPaperServiceEntity = new TestPaperServiceEntity(id, id);
            testPaperServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(testPaperServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TestPaperEntity> queryAll() {
        List<TestPaperEntity> result = new ArrayList<>();
        TableQuery<TestPaperServiceEntity> query = TableQuery
                .from(TestPaperServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(TestPaperEntity.fromServiceEntity(s))
        );
        return result;
    }

}
