package com.ecnu.g03.pethospital.dao;

import com.microsoft.azure.storage.blob.CloudBlockBlob;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

@Repository
public class BlobDao {

//    public boolean uploadPicture(String filename, String base64) {
//        Decoder decoder = Base64.getDecoder();
//        byte[] buffer = decoder.decode(base64);
//        ByteArrayOutputStream out = new ByteArrayOutputStream(buffer);
//        CloudBlockBlob blockBlob = container.GetBlockBlobReference("mypicture.png");
//        using (var fileStream = System.IO.File.OpenRead(file))
//        {
//            // 这是一个同步执行的方法
//            blockBlob.UploadFromStream(fileStream);
//        }
//    }

}