package com.ecnu.g03.pethospital.dto.admin.response.item;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:16
 */
@Data
@NoArgsConstructor
public class ItemDeleteResponse extends BaseResponse {

    private boolean isSuccessful;

}
