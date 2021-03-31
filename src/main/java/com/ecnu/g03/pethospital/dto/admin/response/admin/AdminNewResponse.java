package com.ecnu.g03.pethospital.dto.admin.response.admin;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/3/29 15:01
 */
@Data
@NoArgsConstructor
public class AdminNewResponse extends BaseResponse {

    private AdminEntity admin;

}
