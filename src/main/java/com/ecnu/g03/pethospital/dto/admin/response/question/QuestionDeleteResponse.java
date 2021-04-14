package com.ecnu.g03.pethospital.dto.admin.response.question;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xueying Li
 * @date 2021/4/12 08:47
 */
@NoArgsConstructor
@Data
public class QuestionDeleteResponse extends BaseResponse {

    private boolean isSuccessful;
}
