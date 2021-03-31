package com.ecnu.g03.pethospital.dto.admin.response.admin;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/29 9:52
 */
@Data
@NoArgsConstructor
public class AdminGetAllResponse extends BaseResponse {

    List<AdminEntity> admins;

}
