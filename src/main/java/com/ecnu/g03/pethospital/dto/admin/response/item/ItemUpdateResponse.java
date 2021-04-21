package com.ecnu.g03.pethospital.dto.admin.response.item;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/19 14:15
 */
@Data
@NoArgsConstructor
public class ItemUpdateResponse extends BaseResponse {

    private ItemEntity item;

}
