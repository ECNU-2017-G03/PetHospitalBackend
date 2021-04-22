package com.ecnu.g03.pethospital.dao.blob;

import com.azure.storage.blob.BlobClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class PictureBlobDao extends BaseBlobDao {

    public PictureBlobDao() {
        super("pethospitalpicture");
    }

    public String insertPicture(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            BlobClient blobClient = blobContainerClient.getBlobClient(file.getName());
            blobClient.upload(is, file.getSize());
            return blobClient.getBlobUrl();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }
}
