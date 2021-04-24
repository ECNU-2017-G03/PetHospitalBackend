package com.ecnu.g03.pethospital.dao.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.ecnu.g03.pethospital.dao.blob.constant.VideoType;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Juntao Peng, Jiayi Zhu
 * @date 2021/4/22 16:58
 */
@Repository
public class VideoBlobDao extends BaseBlobDao {

    public VideoBlobDao() {
        super("pethospitalvideo");
    }

    /**
     * Insert video into blob storage. The video is specified by a File object.
     *
     * @apiNote Receiving the entire video file is not a good idea. You should use MultipartFile instead.
     * @param file The video file
     * @param contentType The video type
     * @return The uploaded url in blob storage
     */
    public String insertVideo(File file, VideoType contentType) {
        try (InputStream is = new FileInputStream(file)) {
            BlobClient blobClient = blobContainerClient.getBlobClient(file.getName());
            BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
            switch (contentType) {
                case MP4:
                    blobHttpHeaders.setContentType("video/mp4").setContentLanguage("en-US");
                    break;
                case WMV:
                    blobHttpHeaders.setContentType("video/wmv").setContentLanguage("en-US");
                    break;
            }
            blobClient.upload(is, file.length(), true);
            blobClient.setHttpHeaders(blobHttpHeaders);
            return blobClient.getBlobUrl();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }

    public String insertVideo(MultipartFile file, String contentType) {
        try (InputStream is = new BufferedInputStream(file.getInputStream())) {
            BlobClient blobClient = blobContainerClient.getBlobClient(UUID.randomUUID().toString());
            BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
            blobHttpHeaders.setContentType(contentType).setContentLanguage("en-US");
            blobClient.upload(is, file.getSize(), true);
            blobClient.setHttpHeaders(blobHttpHeaders);
            return blobClient.getBlobUrl();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }
}
