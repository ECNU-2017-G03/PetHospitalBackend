package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.TestRecordEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestRecordServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:11
 */
@Component
public class TestRecordTableDao extends BaseTableDao{
    public TestRecordTableDao() {
        super("testRecord");
    }

    public TestRecordEntity getTestRecordByQuizIdAndSid(String sId, String quizId) {
        try {
            String combinedFilter = TableQuery.combineFilters(TableQuery.generateFilterCondition("QuizId", TableQuery.QueryComparisons.EQUAL, quizId
            ), TableQuery.Operators.AND, TableQuery.generateFilterCondition("Sid", TableQuery.QueryComparisons.EQUAL, sId));
            TableQuery<TestRecordServiceEntity> recordQuery = TableQuery
                    .from(TestRecordServiceEntity.class)
                    .where(combinedFilter);
            for(TestRecordServiceEntity testRecordServiceEntity: cloudTable.execute(recordQuery)) {
                return TestRecordEntity.fromServiceEntity(testRecordServiceEntity);
            }
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<TestRecordEntity> getRecordBysId(String id) {
        try {
            TableQuery<TestRecordServiceEntity> recordQuery = TableQuery
                    .from(TestRecordServiceEntity.class)
                    .where(TableQuery.generateFilterCondition("Sid", TableQuery.QueryComparisons.EQUAL, id));
            List<TestRecordEntity> list = new LinkedList<>();
            for(TestRecordServiceEntity testRecordServiceEntity: cloudTable.execute(recordQuery)) {
                list.add(TestRecordEntity.fromServiceEntity(testRecordServiceEntity));
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
