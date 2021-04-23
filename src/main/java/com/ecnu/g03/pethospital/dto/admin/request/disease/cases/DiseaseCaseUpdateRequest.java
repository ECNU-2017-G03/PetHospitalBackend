package com.ecnu.g03.pethospital.dto.admin.request.disease.cases;

import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/23 23:53
 */
@Data
public class DiseaseCaseUpdateRequest {

    private String id;
    private String name;
    private List<String> disease;
    private String description;
    private String petInfo;
    private List<String> picture;
    private String video;

}
