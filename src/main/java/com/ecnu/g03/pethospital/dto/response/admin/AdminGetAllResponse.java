package com.ecnu.g03.pethospital.dto.response.admin;

import com.ecnu.g03.pethospital.dto.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Shen Lei
 * @Date 2021/3/29 9:52
 */
@Data
@NoArgsConstructor
public class AdminGetAllResponse extends BaseResponse {

    List<AdminEntity> admins;

}
