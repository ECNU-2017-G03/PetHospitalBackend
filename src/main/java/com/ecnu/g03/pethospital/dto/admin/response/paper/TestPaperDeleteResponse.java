package com.ecnu.g03.pethospital.dto.admin.response.paper;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:49
 */
@NoArgsConstructor
@Data
public class TestPaperDeleteResponse extends BaseResponse {

    private boolean isSuccessful;

}
