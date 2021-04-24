package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.blob.PictureBlobDao;
import com.ecnu.g03.pethospital.dao.blob.VideoBlobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Shen Lei
 * @date 2021/4/24 21:27
 */
@Service
public class BlobService {

    private final VideoBlobDao videoBlobDao;
    private final PictureBlobDao pictureBlobDao;

    @Autowired
    public BlobService(VideoBlobDao videoBlobDao, PictureBlobDao pictureBlobDao) {
        this.videoBlobDao = videoBlobDao;
        this.pictureBlobDao = pictureBlobDao;
    }

    public String insertVideo(MultipartFile file) {
        return videoBlobDao.insertVideo(file, file.getContentType());
    }

    public String insertPicture(MultipartFile file) {
        return pictureBlobDao.insertPicture(file, file.getContentType());
    }

}
