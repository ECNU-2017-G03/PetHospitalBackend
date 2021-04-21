package com.ecnu.g03.pethospital.dto.admin.response.paper;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 14:09
 */
@Data
@NoArgsConstructor
public class TestPaperSearchResponse extends BaseResponse {

    private List<TestPaperEntity> testPapers;

}
