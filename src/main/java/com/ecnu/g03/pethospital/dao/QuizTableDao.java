package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuizServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Yiqing Tao, Shen Lei, Xueying Li
 * @date : Created in 2021/3/24 17:37
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
        Date date = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.HOUR, -10);
        Date newDate = cl.getTime();
        System.out.println(newDate);
        System.out.println("newDATE");
        String timeNow = sdf.format(newDate);
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

    public List<QuizEntity> queryAll() {
        List<QuizEntity> result = new ArrayList<>();
        TableQuery<QuizServiceEntity> query = TableQuery
                .from(QuizServiceEntity.class);
        cloudTable.execute(query).forEach(
                (s) -> result.add(QuizEntity.fromServiceEntity(s))
        );
        return result;
    }

    public boolean insert(QuizEntity quiz) {
        QuizServiceEntity quizServiceEntity = (QuizServiceEntity) quiz.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(quizServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(String id) {
        try {
            QuizServiceEntity quizServiceEntity = new QuizServiceEntity(id, id);
            quizServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(quizServiceEntity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
