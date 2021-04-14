package com.ecnu.g03.pethospital.dto.admin.response.quiz;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xueying Li
 * @date 2021/4/12 10:06
 */
@NoArgsConstructor
@Data
public class QuizNewResponse extends BaseResponse {
    QuizEntity quiz;
}
