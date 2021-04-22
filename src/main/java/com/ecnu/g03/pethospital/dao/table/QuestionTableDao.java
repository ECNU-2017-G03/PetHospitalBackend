package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuestionServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 17:06
 */
@Component
public class QuestionTableDao extends BaseTableDao{
    public QuestionTableDao() {
        super("question");
    }

    public QuestionEntity queryQuestionById(String id) {
        TableQuery<QuestionServiceEntity> rangeQuery = TableQuery
                .from(QuestionServiceEntity.class)
                .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
        for(QuestionServiceEntity questionServiceEntity: cloudTable.execute(rangeQuery)) {
            return QuestionEntity.fromServiceEntity(questionServiceEntity);
        }
        return null;
    }
}
