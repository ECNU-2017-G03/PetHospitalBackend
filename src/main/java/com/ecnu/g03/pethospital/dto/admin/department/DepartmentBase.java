package com.ecnu.g03.pethospital.dto.admin.department;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shen Lei
 * @date 2021/3/31 13:36
 */
@Data
@NoArgsConstructor
public class DepartmentBase {

    private String id;
    private String name;
    private String description;

}
