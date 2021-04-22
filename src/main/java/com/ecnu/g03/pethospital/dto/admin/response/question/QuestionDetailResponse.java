package com.ecnu.g03.pethospital.dto.admin.response.question;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/22 22:13
 */
@Data
public class QuestionDetailResponse extends BaseResponse {

    private QuestionEntity question;

}
