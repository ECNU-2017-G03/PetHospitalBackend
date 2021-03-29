package com.ecnu.g03.pethospital.dto.response.admin;

import com.ecnu.g03.pethospital.dto.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/3/29 15:47
 */
@Data
@NoArgsConstructor
public class AdminDetailResponse extends BaseResponse {

    private AdminEntity admin;

}
