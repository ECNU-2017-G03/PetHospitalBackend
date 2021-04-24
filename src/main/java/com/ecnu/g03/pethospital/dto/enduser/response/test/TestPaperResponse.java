package com.ecnu.g03.pethospital.dto.enduser.response.test;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Yiqing Tao
 * @date Created in 2021/3/24 17:21
 */
@Data
@AllArgsConstructor
public class TestPaperResponse {
    private List<QuestionEntity> questionList;
    private String time;
}
