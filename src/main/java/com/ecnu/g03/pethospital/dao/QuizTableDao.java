package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuizServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 17:37
 */
@Component
public class QuizTableDao extends BaseTableDao{
    public QuizTableDao() {
        super("quiz");
    }

    public QuizEntity queryQuizById(String id) {
        TableQuery<QuizServiceEntity> quizQuery = TableQuery
                .from(QuizServiceEntity.class)
                .where(TableQuery.generateFilterCondition("PartitionKey", TableQuery.QueryComparisons.EQUAL, id));
        for(QuizServiceEntity quizServiceEntity: cloudTable.execute(quizQuery)) {
            return QuizEntity.fromServiceEntity(quizServiceEntity);
        }
        return null;
    }

    public List<QuizEntity> queryQuizByStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String timeNow = sdf.format(Calendar.getInstance().getTime().toString());
        System.out.println(timeNow);
        String condition = TableQuery.generateFilterCondition("StartTime", TableQuery.QueryComparisons.GREATER_THAN, timeNow);
        TableQuery<QuizServiceEntity> quizQuery = TableQuery
                .from(QuizServiceEntity.class)
                .where(condition);
        List<QuizEntity> result = new LinkedList<>();
        for(QuizServiceEntity quizServiceEntity: cloudTable.execute(quizQuery)) {
            result.add(QuizEntity.fromServiceEntity(quizServiceEntity));
        }
        System.out.println("size: ");
        System.out.println(result.size());
        return result;
    }
}
