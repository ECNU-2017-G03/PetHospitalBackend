package com.ecnu.g03.pethospital.dto.admin.response.quiz;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:12
 */
@Data
@NoArgsConstructor
public class QuizSearchResponse extends BaseResponse {

    private List<QuizEntity> quizzes;

}
