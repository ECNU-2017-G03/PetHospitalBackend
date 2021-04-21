package com.ecnu.g03.pethospital.dto.admin.response.learner;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:36
 */
@Data
@NoArgsConstructor
public class LearnerUpdateResponse extends BaseResponse {

    private UserEntity user;

}
