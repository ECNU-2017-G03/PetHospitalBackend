package com.ecnu.g03.pethospital.dto.admin.response.blob;

import com.ecnu.g03.pethospital.dto.admin.response.BaseResponse;
import lombok.Data;

/**
 * @author Shen Lei
 * @date 2021/4/24 21:24
 */
@Data
public class BlobUploadResponse extends BaseResponse {

    private String url;

}
