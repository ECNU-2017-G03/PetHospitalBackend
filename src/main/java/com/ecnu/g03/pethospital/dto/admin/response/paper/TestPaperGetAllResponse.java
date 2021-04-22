package com.ecnu.g03.pethospital.dto.admin.response.paper;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:49
 */
@NoArgsConstructor
@Data
public class TestPaperGetAllResponse extends BaseResponse {

    private List<TestPaperEntity> testPapers;

}
