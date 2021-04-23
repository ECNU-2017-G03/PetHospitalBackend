package com.ecnu.g03.pethospital.controller.enduser;

import com.ecnu.g03.pethospital.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    // todo: demo, delete
    @PostMapping(value = "/file")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String url = toolService.uploadVideo(file);
        if (url == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}
