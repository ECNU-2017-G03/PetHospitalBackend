package com.ecnu.g03.pethospital.dto.admin.response.quiz;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuizEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/22 22:26
 */
@Data
public class QuizDetailResponse extends BaseResponse {

    private QuizEntity quiz;

}
