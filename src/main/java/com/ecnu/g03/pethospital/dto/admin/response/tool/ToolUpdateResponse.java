package com.ecnu.g03.pethospital.dto.admin.response.tool;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import lombok.Data;

/**
 * @author Xueying Li
 * @date 2021/4/7 14:11
 */
@Data
public class ToolUpdateResponse extends BaseResponse {

    private ToolEntity tool;

}
