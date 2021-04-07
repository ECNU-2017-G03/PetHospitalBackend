package com.ecnu.g03.pethospital.dto.response.test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/31 13:59
 */
@Data
@AllArgsConstructor
public class TestInfo {
    private String testId;
    private String quizId;
    private String startTime;
    private String testName;
    private String endTime;
    private String duration;
    private String sid;
}
