package com.ecnu.g03.pethospital.dto.admin.response.item;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:16
 */
@Data
@NoArgsConstructor
public class ItemGetAllResponse extends BaseResponse {

    private List<ItemEntity> items;

}
