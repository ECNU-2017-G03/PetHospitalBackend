package com.ecnu.g03.pethospital.Service;

import com.ecnu.g03.pethospital.dao.table.QuizTableDao;
import com.ecnu.g03.pethospital.dao.table.TestRecordTableDao;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.service.UserService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/29 9:21
 */
@Ignore
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    private final String id = "12345";

    @Autowired
    UserService userService;

    @MockBean
    QuizTableDao quizTableDao;

    @Test
    public void testGetQuizByIdSuccess() throws Exception{
        QuizEntity quizEntity= new QuizEntity("startTime", "endTime", "testPaperid", "quizId");
        Mockito.when(quizTableDao.queryQuizById(id)).thenReturn(quizEntity);


    }


}
