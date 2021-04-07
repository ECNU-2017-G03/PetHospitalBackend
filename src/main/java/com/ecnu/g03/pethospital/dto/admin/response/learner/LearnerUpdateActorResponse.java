package com.ecnu.g03.pethospital.dto.admin.response.learner;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:22
 */
@Data
@NoArgsConstructor
public class LearnerUpdateActorResponse extends BaseResponse {

    private UserEntity learner;

}
