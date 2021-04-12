package com.ecnu.g03.pethospital.dto.admin.response.question;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xueying Li
 * @date 2021/4/12 08:50
 */
@NoArgsConstructor
@Data
public class QuestionNewResponse extends BaseResponse {

    QuestionEntity question;

}
