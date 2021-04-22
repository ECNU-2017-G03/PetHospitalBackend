package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.*;
import com.ecnu.g03.pethospital.dto.enduser.response.test.PastTestResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.QuizResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestReadyResponse;
import com.ecnu.g03.pethospital.dto.enduser.response.test.TestRecordResponse;
import com.ecnu.g03.pethospital.dto.response.test.*;
import com.ecnu.g03.pethospital.model.entity.*;
import com.ecnu.g03.pethospital.model.parse.QuestionRecord;
import com.ecnu.g03.pethospital.model.parse.Questions;
import com.ecnu.g03.pethospital.model.parse.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 17:53
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

    public QuizResponse getQuizById(String id) {
        QuizResponse quizResponse =null;
        QuizEntity quizEntity = quizTableDao.queryQuizById(id);
        if(quizEntity != null) {
            quizResponse = new QuizResponse(quizEntity.getStartTime(), quizEntity.getEndTime(), id);
            TestPaperEntity testPaperEntity = testPaperTableDao.queryTestPaper(quizEntity.getTestPaperId());
            for (Questions question : testPaperEntity.getQuestionIdList()) {
                QuestionEntity questionEntity = questionTableDao.queryQuestionById(question.getQid());
                questionEntity.setAnswer("");
                questionEntity.setScore(Integer.parseInt(question.getScore()));
                quizResponse.getQuestions().add(questionEntity);
            }
        }
        if(quizResponse == null) {
            return new QuizResponse();
        }
        return quizResponse;
    }

    public TestRecordResponse getTestRecordByQuizIdAndSid(String sid, String quizId) {
        TestRecordEntity testRecordEntity = testRecordTableDao.getTestRecordByQuizIdAndSid(sid, quizId);
        TestRecordResponse testRecordResponse = null;
        if(testRecordEntity != null) {
            testRecordResponse = new TestRecordResponse(testRecordEntity.getScore(), sid, quizId);
            TestResultEntity testResultEntity = testResultTableDao.getTestResultBySidAndQuizId(testRecordEntity.getTestPaperId(), sid);
            for(QuestionRecord questionRecord: testResultEntity.getRecordList()) {
                QuestionEntity questionEntity = questionTableDao.queryQuestionById(questionRecord.getQid());
                if(questionEntity != null) {
                    questionEntity.setScore(Integer.parseInt(questionRecord.getScore()));
                    testRecordResponse.getQuestionEntityList().add(questionEntity);
                }
            }
        }
        if(testRecordResponse == null) {
            return new TestRecordResponse();
        }
        return testRecordResponse;
    }

    public PastTestResponse getPastTestBySid(String id) {
        List<TestRecordEntity> list = testRecordTableDao.getRecordBysId(id);
        PastTestResponse pastTestResponse = null;
        if(list != null) {
            pastTestResponse = new PastTestResponse();
            for(TestRecordEntity testRecordEntity: list) {
                pastTestResponse.getPidList().add(testRecordEntity.getTestPaperId());
                pastTestResponse.getQidList().add(testRecordEntity.getQuizId());
                pastTestResponse.getScoreList().add(testRecordEntity.getScore());
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
        for(QuizEntity quizEntity: quizList) {
            List<Student> sidList = quizEntity.getStudentIdList();
            for(Student stu: sidList) {
                if(stu.getSid().equals(sid)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    try{
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
        }
        System.out.println("test size" + testReadyResponse.getTestInfo().size() );
        return testReadyResponse;
    }
}
