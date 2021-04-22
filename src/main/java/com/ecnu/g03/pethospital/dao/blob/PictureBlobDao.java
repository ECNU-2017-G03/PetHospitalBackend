package com.ecnu.g03.pethospital.dao.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.ecnu.g03.pethospital.dao.blob.constant.ImageType;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class PictureBlobDao extends BaseBlobDao {

    public PictureBlobDao() {
        super("pethospitalpicture");
    }

    public String insertPicture(File file, ImageType contentType) {
        try (InputStream is = new FileInputStream(file)) {
            BlobClient blobClient = blobContainerClient.getBlobClient(file.getName());
            BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
            switch (contentType) {
                case JPEG:
                    blobHttpHeaders.setContentType("image/jpeg").setContentLanguage("en-US");
                    break;
                case PNG:
                    blobHttpHeaders.setContentType("image/png").setContentLanguage("en-US");
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
}
