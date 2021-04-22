package com.ecnu.g03.pethospital.dto.admin.response.learner;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:36
 */
@NoArgsConstructor
@Data
public class LearnerGetAllResponse extends BaseResponse {

    private List<UserEntity> users;

}
