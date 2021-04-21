package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.QuestionServiceEntity;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/22 10:27
 */
@Setter
@Getter
public class QuestionEntity extends BaseEntity{
    private String answer;
    private String content;
    private String disease;
    private Map<String, String> options;
    private int score;
    private String qid;

//    public QuestionEntity(String answer, String content, String disease, Map<String, String> options, int score) {
////        super(UUID.randomUUID().toString());
////        this.answer = answer;
////        this.content = content;
////        this.disease = disease;
////        this.options = options;
////    }

    public QuestionEntity(String id, String answer, String content, String disease) {
        super(id);
        this.qid = id;
        this.answer = answer;
        this.content = content;
        this.disease = disease;
    }

    public static QuestionEntity fromServiceEntity(QuestionServiceEntity questionServiceEntity) {
        System.out.println("form");
        System.out.println(questionServiceEntity.getPartitionKey());
        QuestionEntity questionEntity = new QuestionEntity(questionServiceEntity.getPartitionKey(), questionServiceEntity.getAnswer(), questionServiceEntity.getContent(), questionServiceEntity.getDisease());
        String options = questionServiceEntity.getOption();
        questionEntity.setOptions(gson.fromJson(options, new TypeToken<HashMap<String, String>>(){}.getType()));
        return questionEntity;
    }


}
