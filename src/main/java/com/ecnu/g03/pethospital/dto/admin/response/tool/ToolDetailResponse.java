package com.ecnu.g03.pethospital.dto.admin.response.tool;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.BaseEntity;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/22 22:29
 */
@Data
public class ToolDetailResponse extends BaseResponse {

    private ToolEntity tool;

}
