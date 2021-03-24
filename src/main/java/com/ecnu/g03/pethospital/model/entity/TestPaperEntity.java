package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:37
 */
@Getter
@Setter
public class TestPaperEntity {
    private List<String> questionIdList;
    private List<String> scoreList;
    private int questionSize;

    public static TestPaperEntity fromServiceEntity(TestPaperServiceEntity testPaperServiceEntity) {
        TestPaperEntity testPaperEntity = new TestPaperEntity();
        String questions = testPaperServiceEntity.getQuestions();
        System.out.println(questions);
        return testPaperEntity;
    }

}
