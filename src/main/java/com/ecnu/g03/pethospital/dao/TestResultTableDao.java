package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.TestResultEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestResultServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:44
 */
@Component
public class TestResultTableDao extends BaseTableDao{
    public TestResultTableDao() {
        super("testResult");
    }

    public TestResultEntity getTestResultBySidAndQuizId(String paperId, String sId) {
        String combinedFilter = TableQuery.combineFilters(TableQuery.generateFilterCondition("Pid", TableQuery.QueryComparisons.EQUAL, paperId
        ), TableQuery.Operators.AND, TableQuery.generateFilterCondition("Sid", TableQuery.QueryComparisons.EQUAL, sId));
        TableQuery<TestResultServiceEntity> resultQuery = TableQuery
                .from(TestResultServiceEntity.class)
                .where(combinedFilter);
        for(TestResultServiceEntity testResultServiceEntity: cloudTable.execute(resultQuery)) {
            return TestResultEntity.fromServiceEntity(testResultServiceEntity);
        }
        return null;
    }
 }
