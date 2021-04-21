package com.ecnu.g03.pethospital.dto.admin.response.tool;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xueying Li
 * @date 2021/4/7 13:49
 */
@Data
@NoArgsConstructor
public class ToolNewResponse extends BaseResponse {
    private ToolEntity tool;
}
