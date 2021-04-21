package com.ecnu.g03.pethospital.dto.admin.request.disease.cases;

import lombok.Data;

import java.util.List;

/**
 * @author Xueying Li
 * @date 2021/4/12 10:35
 */
@Data
public class DiseaseCaseNewRequest {

    private String name;
    private String disease;
    private String description;
    private String petInfo;
    private String picture;
    private String video;

}
