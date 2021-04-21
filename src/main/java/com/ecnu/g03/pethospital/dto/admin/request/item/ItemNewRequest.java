package com.ecnu.g03.pethospital.dto.admin.request.item;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/7 14:19
 */
@Data
public class ItemNewRequest {

    private String description;
    private String name;
    private Integer price;
    private String time;

}
