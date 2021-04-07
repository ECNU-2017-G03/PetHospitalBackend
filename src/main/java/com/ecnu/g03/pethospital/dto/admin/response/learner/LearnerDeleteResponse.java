package com.ecnu.g03.pethospital.dto.admin.response.learner;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:37
 */
@Data
@NoArgsConstructor
public class LearnerDeleteResponse extends BaseResponse {

    private boolean isSuccessful;

}
