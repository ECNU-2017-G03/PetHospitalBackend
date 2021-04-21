package com.ecnu.g03.pethospital.dto.admin.response.question;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/12 8:21
 */
@Data
public class QuestionGetAllResponse extends BaseResponse {

    List<QuestionEntity> questions;

}
