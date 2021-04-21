package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.parse.Questions;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:37
 */
@Getter
@Setter
@NoArgsConstructor
public class TestPaperEntity extends BaseEntity {

    @JsonProperty("questions")
    private List<Questions> questionIdList;
    private int questionSize;

    public TestPaperEntity(String id) {
        super(id);
    }

    public static TestPaperEntity fromServiceEntity(TestPaperServiceEntity testPaperServiceEntity) {
        TestPaperEntity testPaperEntity = new TestPaperEntity();
        String questions = testPaperServiceEntity.getQuestions();
        List<Questions> list = gson.fromJson(questions, new TypeToken<List<Questions>>(){}.getType());
        testPaperEntity.questionIdList = list;
        testPaperEntity.questionSize = list.size();
        testPaperEntity.setId(testPaperServiceEntity.getPartitionKey());
        return testPaperEntity;
    }

}
