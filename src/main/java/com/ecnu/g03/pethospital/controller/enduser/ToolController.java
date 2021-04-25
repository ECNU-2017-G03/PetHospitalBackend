package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiayi Zhu
 * @date 2021-03-29 22:17
 */
@RestController
@RequestMapping("/user/tool")
public class ToolController {
    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }
}
