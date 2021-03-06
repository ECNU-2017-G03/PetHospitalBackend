package com.ecnu.g03.pethospital.model.parse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-29 20:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDetail {
    private String overview;
    private List<String> members;
    private List<String> tools;
    private String description;
}
