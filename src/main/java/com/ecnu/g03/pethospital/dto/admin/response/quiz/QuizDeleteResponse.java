package com.ecnu.g03.pethospital.dto.admin.response.quiz;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xueying Li
 * @date 2021/4/12 10:05
 */
@NoArgsConstructor
@Data
public class QuizDeleteResponse extends BaseResponse {
    private boolean isSuccessful;
}
