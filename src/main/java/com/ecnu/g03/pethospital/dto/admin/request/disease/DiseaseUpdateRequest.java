package com.ecnu.g03.pethospital.dto.admin.request.disease;

import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/14 14:16
 */
@Data
public class DiseaseUpdateRequest {

    String id;
    String name;
    String description;

}
