package com.ecnu.g03.pethospital.dto.admin.request.learner;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:39
 */
@Data
public class LearnerNewRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("password")
    private String password;
    @JsonProperty("actor")
    private List<String> actor;

}
