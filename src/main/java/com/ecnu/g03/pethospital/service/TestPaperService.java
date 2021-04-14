package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.TestPaperTableDao;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.model.parse.Questions;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 15:28
 */
@Service
public class TestPaperService {

    private final TestPaperTableDao testPaperTableDao;

    TestPaperService(TestPaperTableDao testPaperTableDao) {
        this.testPaperTableDao = testPaperTableDao;
    }

    public TestPaperEntity insert(String questions) {
        String uuid = UUID.randomUUID().toString();
        if (!testPaperTableDao.insert(uuid, questions)) {
            return null;
        }
        Gson gson = new Gson();
        TestPaperEntity testPaperEntity = new TestPaperEntity(uuid);
        List<Questions> list = gson.fromJson(questions, new TypeToken<List<Questions>>(){}.getType());
        testPaperEntity.setQuestionIdList(list);
        testPaperEntity.setQuestionSize(list.size());
        return testPaperEntity;
    }

    public boolean deleteById(String id) {
        return testPaperTableDao.deleteById(id);
    }

    public List<TestPaperEntity> queryAll() {
        return testPaperTableDao.queryAll();
    }

    public List<TestPaperEntity> searchById(String id) {
        List<TestPaperEntity> testPapers = new ArrayList<>();
        TestPaperEntity testPaper = testPaperTableDao.queryTestPaper(id);
        if (testPaper != null) {
            testPapers.add(testPaper);
        }
        return testPapers;
    }

}
