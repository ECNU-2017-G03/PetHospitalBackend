package com.ecnu.g03.pethospital.dto.admin.request.tool;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/23 23:21
 */
@Data
public class ToolUpdateRequest {

    private String id;
    private String name;
    private String description;
    private String picture;

}
