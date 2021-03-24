package com.ecnu.g03.pethospital.dto.response;

import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;

import java.util.List;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/24 17:21
 */
@Data
public class TestPaperResponse {
    private List<QuestionEntity> questionList;
    private String time;
}
