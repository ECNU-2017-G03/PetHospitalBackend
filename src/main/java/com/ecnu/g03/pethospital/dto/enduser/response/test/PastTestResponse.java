package com.ecnu.g03.pethospital.dto.enduser.response.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 20:26
 */
@Data
@AllArgsConstructor
public class PastTestResponse {
    private List<String> qidList;
    private List<Integer> scoreList;
    private List<String> pidList;

    public PastTestResponse() {
        this.qidList = new ArrayList<>();
        this.pidList = new ArrayList<>();
        this.scoreList = new ArrayList<>();
    }
}
