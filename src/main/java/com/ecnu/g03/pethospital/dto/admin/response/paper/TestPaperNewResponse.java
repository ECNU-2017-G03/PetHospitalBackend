package com.ecnu.g03.pethospital.dto.admin.response.paper;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:34
 */
@Data
@NoArgsConstructor
public class TestPaperNewResponse extends BaseResponse {

    private TestPaperEntity testPaper;

}
