package com.ecnu.g03.pethospital.dto.admin.request.blob;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Shen Lei
 * @date 2021/4/24 21:17
 */
@Data
public class BlobUploadRequest {

    private MultipartFile file;

}
