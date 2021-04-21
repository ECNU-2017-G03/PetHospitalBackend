package com.ecnu.g03.pethospital.dto.admin.request.item;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/19 14:19
 */
@Data
public class ItemUpdateRequest {

    private String id;
    private String name;
    private Integer price;
    private String description;
    private String time;

}