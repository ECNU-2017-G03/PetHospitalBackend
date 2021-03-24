package com.ecnu.g03.pethospital.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 20:58
 */
@Data
public class TestReadyResponse {
    private List<String> paperId;
    private List<String> quizId;
    private String sid;

    public TestReadyResponse(String sid) {
        this.sid = sid;
        this.paperId = new ArrayList<>();
        this.quizId = new ArrayList<>();
    }
}
