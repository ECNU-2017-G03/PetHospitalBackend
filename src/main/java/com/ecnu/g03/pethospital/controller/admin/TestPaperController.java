package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.service.TestPaperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shen Lei
 * @date 2021/4/7 15:27
 */
@RestController
@RequestMapping(value = "/admin/paper", produces = "application/json; charset=UTF-8")
public class TestPaperController {

    private final TestPaperService testPaperService;

    TestPaperController(TestPaperService testPaperService) {
        this.testPaperService = testPaperService;
    }

    @GetMapping("/all")

    @GetMapping("/delete")

    @PostMapping("/new")

}
