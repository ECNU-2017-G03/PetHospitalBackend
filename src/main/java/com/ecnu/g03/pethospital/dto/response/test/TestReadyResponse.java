package com.ecnu.g03.pethospital.dto.response.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 20:58
 */
@Data
@AllArgsConstructor
public class TestReadyResponse {
    private List<TestInfo> testInfo;

    public TestReadyResponse(String sid) {
        this.testInfo= new ArrayList<>();
    }
}
