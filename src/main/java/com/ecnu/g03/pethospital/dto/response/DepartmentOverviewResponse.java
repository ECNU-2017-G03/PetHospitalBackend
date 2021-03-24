package com.ecnu.g03.pethospital.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 23:20
 */
@Data
@AllArgsConstructor
public class DepartmentOverviewResponse {
    List<String> departmentList;
}
