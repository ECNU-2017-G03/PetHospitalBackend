package com.ecnu.g03.pethospital.dto.admin.response.admin;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/31 14:58
 */
@Data
public class AdminSearchResponse extends BaseResponse {

    private List<AdminEntity> admins;

}
