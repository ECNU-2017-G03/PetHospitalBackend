package com.ecnu.g03.pethospital.dto.admin.response.quiz;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/12 09:37
 */
@Data
public class QuizGetAllResponse extends BaseResponse {

    List<QuizEntity> quizzes;

}
