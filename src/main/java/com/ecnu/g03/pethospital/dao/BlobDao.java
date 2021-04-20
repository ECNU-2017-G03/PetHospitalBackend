package com.ecnu.g03.pethospital.dao;

public class BlobDao {

    public uploadPicture(String filename, String base64) {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        CloudBlockBlob blockBlob = container.GetBlockBlobReference("mypicture.png");
        using (var fileStream = System.IO.File.OpenRead(file))
        {
            // 这是一个同步执行的方法
            blockBlob.UploadFromStream(fileStream);
        }
    }

}