package com.ecnu.g03.pethospital.dto.enduser.response.test;

import com.ecnu.g03.pethospital.model.parse.TestRecord;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date Created in 2021/3/24 20:26
 */
@Data
@AllArgsConstructor
public class PastTestResponse {
    private List<TestRecord> records;

    public PastTestResponse() {
        this.records = new ArrayList<>();
    }
}
