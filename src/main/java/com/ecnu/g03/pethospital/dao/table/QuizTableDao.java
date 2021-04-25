package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.dao.table.util.TableDaoUtils;
import com.ecnu.g03.pethospital.model.entity.*;
import com.ecnu.g03.pethospital.model.serviceentity.AdminServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.QuizServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
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

    public List<QuizEntity> queryQuizByIdVague(String id) {
        List<QuizEntity> result = new ArrayList<>();
        try {
            TableQuery<QuizServiceEntity> quizQuery = TableQuery
                    .from(QuizServiceEntity.class)
                    .where(TableDaoUtils.containsPrefix("PartitionKey", id));
            Iterable<QuizServiceEntity> nameResult = cloudTable.execute(quizQuery);
            nameResult.forEach(r->result.add(QuizEntity.fromServiceEntity(r)));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public List<QuizEntity> queryQuizByStartTime() {
        String timeNow = Instant.now().toString();
        String condition = TableQuery.generateFilterCondition("EndTime", TableQuery.QueryComparisons.GREATER_THAN_OR_EQUAL, timeNow);
        TableQuery<QuizServiceEntity> quizQuery = TableQuery
                .from(QuizServiceEntity.class)
                .where(condition);
        List<QuizEntity> result = new LinkedList<>();
        for(QuizServiceEntity quizServiceEntity: cloudTable.execute(quizQuery)) {
            result.add(QuizEntity.fromServiceEntity(quizServiceEntity));
        }
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

    public QuizEntity update(QuizEntity quizEntity) {
        try {
            QuizServiceEntity quizServiceEntity = quizEntity.toServiceEntity();
            quizServiceEntity.setEtag("*");
            TableOperation operation = TableOperation.merge(quizServiceEntity);
            cloudTable.execute(operation);
            return quizEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
