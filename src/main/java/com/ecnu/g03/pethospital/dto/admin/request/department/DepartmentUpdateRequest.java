package com.ecnu.g03.pethospital.dto.admin.request.department;

import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/21 14:55
 */
@Data
public class DepartmentUpdateRequest {

    private String id;
    private String description;
    private List<String> members;
    private String overview;
    private List<String> tools;

}
