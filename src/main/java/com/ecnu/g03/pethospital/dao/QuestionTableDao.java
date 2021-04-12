package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuestionServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuizServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： Yiqing Tao, Xueying Li
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

    public List<QuestionEntity> queryAll() {
        List<QuestionEntity> result = new ArrayList<>();
        TableQuery<QuestionServiceEntity> query = TableQuery
                .from(QuestionServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(QuestionEntity.fromServiceEntity(s))
        );
        return result;
    }

    public boolean insert(QuestionEntity question) {
        QuestionServiceEntity questionServiceEntity = (QuestionServiceEntity) question.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(questionServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(String id) {
        try {
            QuestionServiceEntity questionServiceEntity = new QuestionServiceEntity(id, id);
            questionServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(questionServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
