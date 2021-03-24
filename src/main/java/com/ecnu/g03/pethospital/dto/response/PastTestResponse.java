package com.ecnu.g03.pethospital.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 20:26
 */
@Data
public class PastTestResponse {
    private List<String> qidList;
    private List<Integer> scoreList;
    private List<String> pidList;

    public PastTestResponse(List<String> qidList, List<Integer> scoreList, List<String> pidList) {
        this.qidList = qidList;
        this.scoreList = scoreList;
        this.pidList = pidList;
    }

    public PastTestResponse() {
        this.qidList = new ArrayList<>();
        this.pidList = new ArrayList<>();
        this.scoreList = new ArrayList<>();
    }
}
