package com.ecnu.g03.pethospital.dto.admin.response.question;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.QuestionEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:11
 */
@Data
@NoArgsConstructor
public class QuestionSearchResponse extends BaseResponse {

    private List<QuestionEntity> questions;

}
