package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.parse.Questions;
import com.ecnu.g03.pethospital.model.serviceentity.QuestionServiceEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.ecnu.g03.pethospital.model.entity.BaseEntity.gson;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:37
 */
@Getter
@Setter
public class TestPaperEntity {
    private List<Questions> questionIdList;
    private int questionSize;

    public static TestPaperEntity fromServiceEntity(TestPaperServiceEntity testPaperServiceEntity) {
        TestPaperEntity testPaperEntity = new TestPaperEntity();
        String questions = testPaperServiceEntity.getQuestions();
        List<Questions> list = gson.fromJson(questions, new TypeToken<List<Questions>>(){}.getType());
        testPaperEntity.questionIdList = list;
        testPaperEntity.questionSize = list.size();
        return testPaperEntity;
    }

}
