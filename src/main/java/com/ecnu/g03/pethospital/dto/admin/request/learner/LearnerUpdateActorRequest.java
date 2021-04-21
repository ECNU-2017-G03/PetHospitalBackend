package com.ecnu.g03.pethospital.dto.admin.request.learner;

import lombok.Data;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:13
 */
@Data
public class LearnerUpdateActorRequest {

    private String id;
    private List<String> actors;

}
