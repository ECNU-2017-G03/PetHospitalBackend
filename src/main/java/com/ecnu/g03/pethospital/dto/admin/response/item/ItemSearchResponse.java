package com.ecnu.g03.pethospital.dto.admin.response.item;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/14 13:49
 */
@Data
@NoArgsConstructor
public class ItemSearchResponse extends BaseResponse {

    private List<ItemEntity> items;

}
