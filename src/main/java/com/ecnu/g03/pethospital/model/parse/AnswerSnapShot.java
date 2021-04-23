package com.ecnu.g03.pethospital.model.parse;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yiqing Tao
 * @date Created in 2021/4/11 12:54
 */
@Data
@AllArgsConstructor
public class AnswerSnapShot {
    private String qid;
    private String choice;

}
