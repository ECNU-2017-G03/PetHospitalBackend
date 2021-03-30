package com.ecnu.g03.pethospital.dto.response;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Shen Lei
 * @Date 2021/3/28 22:41
 */
@Data
@NoArgsConstructor
public class BaseResponse {

    private String message;
    private ResponseStatus status;

}
