package com.ecnu.g03.pethospital.dto.admin.response.quiz;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:12
 */
@Data
@NoArgsConstructor
public class QuizUpdateResponse extends BaseResponse {

    private QuizEntity quiz;

}
