package com.ecnu.g03.pethospital.dto.admin.response.item;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.BaseEntity;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/22 22:28
 */
@Data
public class ItemDetailResponse extends BaseResponse {

    private ItemEntity item;

}
