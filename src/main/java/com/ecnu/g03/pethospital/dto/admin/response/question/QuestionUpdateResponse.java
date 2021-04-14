package com.ecnu.g03.pethospital.dto.admin.response.question;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.BaseEntity;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/14 14:27
 */
@Data
@NoArgsConstructor
public class QuestionUpdateResponse extends BaseResponse {

    QuestionEntity question;

}
