package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.QuizTableDao;
import org.springframework.stereotype.Service;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:28
 */
@Service
public class QuizService {

    private final QuizTableDao quizTableDao;

    QuizService(QuizTableDao quizTableDao) {
        this.quizTableDao = quizTableDao;
    }

}
