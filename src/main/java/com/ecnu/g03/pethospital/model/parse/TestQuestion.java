package com.ecnu.g03.pethospital.model.parse;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yiqing Tao
 * @date Created in 2021/4/7 15:05
 */
@Data
@AllArgsConstructor
public class TestQuestion {
    private String qid;
    private String choice;
    private int score;
}
