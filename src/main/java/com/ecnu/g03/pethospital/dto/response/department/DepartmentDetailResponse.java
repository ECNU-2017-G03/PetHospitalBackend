package com.ecnu.g03.pethospital.dto.response.department;

import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import com.ecnu.g03.pethospital.model.parse.DepartmentDetail;
import lombok.Data;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-29 22:34
 */
@Data
public class DepartmentDetailResponse {
    private String name;
    private String overview;
    private List<String> members;
    private List<ToolEntity> tools;
    private String description;

    public DepartmentDetailResponse(DepartmentDetail detail, List<ToolEntity> toolList, String name) {
        this.overview = detail.getOverview();
        this.members = detail.getMembers();
        this.tools = toolList;
        this.description = detail.getDescription();
        this.name = name;
    }
}
