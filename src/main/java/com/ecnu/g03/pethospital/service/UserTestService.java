package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.*;
import com.ecnu.g03.pethospital.dto.enduser.response.test.PastTestResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.QuizResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestReadyResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestRecordResponse;
import com.ecnu.g03.pethospital.dto.response.test.*;
import com.ecnu.g03.pethospital.model.entity.*;
import com.ecnu.g03.pethospital.model.parse.*;
import com.ecnu.g03.pethospital.model.status.SubmitTestStatus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * provide service for user test module, including participating exams and review test records
 * @author Yiqing Tao
 * @date Created in 2021/3/24 17:53
 */
@Service
public class UserTestService {
    private final QuizTableDao quizTableDao;
    private final TestPaperTableDao testPaperTableDao;
    private final QuestionTableDao questionTableDao;
    private final TestRecordTableDao testRecordTableDao;
    private final TestResultTableDao testResultTableDao;

    @Autowired
    public UserTestService(QuizTableDao quizTableDao, TestPaperTableDao testPaperTableDao, QuestionTableDao questionTableDao, TestRecordTableDao testRecordTableDao, TestResultTableDao testResultTableDao) {
        this.quizTableDao = quizTableDao;
        this.testPaperTableDao = testPaperTableDao;
        this.questionTableDao = questionTableDao;
        this.testRecordTableDao = testRecordTableDao;
        this.testResultTableDao = testResultTableDao;
    }

    public boolean checkTestValid(String sid, String quizId) {
        List<TestRecordEntity> list = testRecordTableDao.getRecordBysId(sid);
        for(TestRecordEntity testRecordEntity: list) {
            if(testRecordEntity.getQuizId().equals(quizId)) {
                return false;
            }
        }
        return true;
    }
    private void structTestPaper(QuizResponse quizResponse, String testPaperId, boolean answerShow) {
        TestPaperEntity testPaperEntity = testPaperTableDao.queryTestPaper(testPaperId);
        for (Questions question : testPaperEntity.getQuestionIdList()) {
            QuestionEntity questionEntity = questionTableDao.queryQuestionById(question.getQid());
            if(!answerShow) {
                questionEntity.setAnswer("");
            }
            questionEntity.setScore(Integer.parseInt(question.getScore()));
            quizResponse.getQuestions().add(questionEntity);
        }
    }

    public QuizResponse getQuizById(String id, boolean answerShow) {
        //first check whether did it before.
        QuizResponse quizResponse = null;
        QuizEntity quizEntity = quizTableDao.queryQuizById(id);
        if(quizEntity != null) {
            quizResponse = new QuizResponse(quizEntity.getStartTime(), quizEntity.getEndTime(), id, quizEntity.getTestPaperId());
            structTestPaper(quizResponse, quizEntity.getTestPaperId(), answerShow);
        }
        if(quizResponse == null) {
            return new QuizResponse();
        }
        return quizResponse;
    }

    public SubmitTestStatus submitTest(List<TestQuestion> questions,String testPaperId, String sid, String quizId, String startTime, String endTime) {
        int actualScore = 0;
        int total = 0;
        List<AnswerSnapShot> answers = new ArrayList<>();
        for(TestQuestion q: questions) {
            QuestionEntity questionEntity = questionTableDao.queryQuestionById(q.getQid());
            if(q.getChoice().equals(questionEntity.getAnswer())) {
                actualScore += q.getScore();
            }
            AnswerSnapShot answerSnapShot = new AnswerSnapShot(q.getQid(), q.getChoice());
            answers.add(answerSnapShot);
            total += q.getScore();
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String submitTime = sdf.format(date).toString();
        TestRecordEntity testRecordEntity = new TestRecordEntity(testPaperId, sid, quizId, actualScore, submitTime
        , startTime, endTime, total);
        testRecordEntity.setAnswerSnapShot(answers);
        if(testRecordTableDao.insertTestRecord(testRecordEntity)) {
            return SubmitTestStatus.OK;
        }
        return SubmitTestStatus.ERROR;

    }

    public PastTestResponse getPastTestBySid(String id) {
        List<TestRecordEntity> list = testRecordTableDao.getRecordBysId(id);
        PastTestResponse pastTestResponse = null;
        if(list != null) {
            pastTestResponse = new PastTestResponse();
            for(TestRecordEntity testRecordEntity: list) {
                TestRecord testRecord = new TestRecord(testRecordEntity.getId(),
                        testRecordEntity.getTestPaperId(), testRecordEntity.getQuizId(), testRecordEntity.getStartTime(),
                        testRecordEntity.getSubmitTime(), testRecordEntity.getStudentId(),
                        testRecordEntity.getTotal(), testRecordEntity.getScore(), testRecordEntity.getEndTime(), testRecordEntity.getAnswerSnapShot());
                pastTestResponse.getRecords().add(testRecord);
            }
        }
        if(pastTestResponse == null) {
            return new PastTestResponse();
        }
        return pastTestResponse;
    }

    public TestReadyResponse getTestForUser(String sid) {
        List<QuizEntity> quizList = quizTableDao.queryQuizByStartTime();
        TestReadyResponse testReadyResponse = new TestReadyResponse(sid);
        List<TestRecordEntity> list = testRecordTableDao.getRecordBysId(sid);
        List<QuizEntity> filter = new ArrayList<>();
        for(QuizEntity quizEntity: quizList) {
            boolean flag = false;
            List<Student> sidList = quizEntity.getStudentIdList();
            for(Student stu: sidList) {
                if(sid.equals(stu.getSid())) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
                filter.add(quizEntity);
            }
        }
        for(QuizEntity quizEntity: filter) {
            boolean flag = true;
            for(TestRecordEntity testRecordEntity: list) {
                if (testRecordEntity.getQuizId().equals(quizEntity.getQuizId())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                try {
                    Date startTime = sdf.parse(quizEntity.getStartTime());
                    Date endTime = sdf.parse(quizEntity.getEndTime());
                    long diff = endTime.getTime() - startTime.getTime();
                    long timeDiff = diff / (1000 * 24);
                    TestInfo testInfo = new TestInfo(quizEntity.getTestPaperId(), quizEntity.getQuizId(),
                            quizEntity.getStartTime(), "", quizEntity.getEndTime(), String.valueOf(timeDiff), sid);
                    testReadyResponse.getTestInfo().add(testInfo);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return testReadyResponse;
    }
}
