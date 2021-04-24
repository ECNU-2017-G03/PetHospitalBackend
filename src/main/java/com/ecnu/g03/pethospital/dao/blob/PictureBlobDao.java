package com.ecnu.g03.pethospital.dao.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.ecnu.g03.pethospital.dao.blob.constant.ImageType;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Juntao Peng, Shen Lei
 * @date 2021/4/22 16:58
 */
@Repository
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

    public String insertPicture(String base64, String filename, ImageType contentType) {
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            int index = base64.indexOf("base64,")+7;
            String newImage = base64.substring(index, base64.length());//去除前缀
            byte[] buffer = decoder.decode(newImage);
            InputStream input = new ByteArrayInputStream(buffer);
            BlobClient blobClient = blobContainerClient.getBlobClient(filename);
            BlobHttpHeaders blobHttpHeaders = new BlobHttpHeaders();
            switch (contentType) {
                case JPEG:
                    blobHttpHeaders.setContentType("image/jpeg").setContentLanguage("en-US");
                    break;
                case PNG:
                    blobHttpHeaders.setContentType("image/png").setContentLanguage("en-US");
                    break;
                default:
                    return null;
            }
            blobClient.upload(input, buffer.length, true);
            blobClient.setHttpHeaders(blobHttpHeaders);
            return blobClient.getBlobUrl();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }

}
