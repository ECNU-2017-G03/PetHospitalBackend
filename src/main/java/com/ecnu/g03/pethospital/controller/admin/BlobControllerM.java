package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.blob.BlobUploadRequest;
import com.ecnu.g03.pethospital.dto.admin.response.blob.BlobUploadResponse;
import com.ecnu.g03.pethospital.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Shen Lei
 * @date 2021/4/24 21:12
 */
@RestController
@RequestMapping(value = "/admin/upload", produces = "application/json; charset=UTF-8")
public class BlobControllerM {

    private final BlobService blobService;

    @Autowired
    public BlobControllerM(BlobService blobService) {
        this.blobService = blobService;
    }

    @PostMapping("/picture")
    public BlobUploadResponse uploadPicture(@RequestParam("file") MultipartFile file) {
        System.out.print("start upload");
        BlobUploadResponse response = new BlobUploadResponse();
        String url = blobService.insertPicture(file);
        if (url == null || url.length() == 0) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setUrl(url);
        return response;
}

    @PostMapping(value = "/video", consumes = "multipart/form-data; charset=UTF-8")
    public BlobUploadResponse uploadVideo(@RequestParam("file") MultipartFile file) {
        System.out.print("start upload");
        BlobUploadResponse response = new BlobUploadResponse();
        String url = blobService.insertVideo(file);
        if (url == null || url.length() == 0) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setUrl(url);
        return response;
    }

}
