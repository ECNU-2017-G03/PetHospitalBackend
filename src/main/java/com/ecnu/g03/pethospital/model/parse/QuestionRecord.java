package com.ecnu.g03.pethospital.model.parse;

import lombok.Data;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 19:49
 */
@Data
public class QuestionRecord {
    private String qid;
    private String score;

    public QuestionRecord(String qid, String score) {
        this.qid = qid;
        this.score = score;
    }
}
