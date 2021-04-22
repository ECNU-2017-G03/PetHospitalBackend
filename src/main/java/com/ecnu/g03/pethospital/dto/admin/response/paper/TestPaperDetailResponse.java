package com.ecnu.g03.pethospital.dto.admin.response.paper;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/22 22:27
 */
@Data
public class TestPaperDetailResponse extends BaseResponse {

    private TestPaperEntity testPaper;

}
