package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.blob.PictureBlobDao;
import com.ecnu.g03.pethospital.dao.blob.VideoBlobDao;
import com.ecnu.g03.pethospital.dao.blob.constant.ImageType;
import com.ecnu.g03.pethospital.dao.blob.constant.VideoType;
import com.ecnu.g03.pethospital.dao.table.ToolTableDao;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jiayi Zhu, Xueying Li, Shen Lei
 * @date 2021-03-29 22:14
 */
@Service
public class ToolService {

    private final ToolTableDao toolTableDao;
    private final PictureBlobDao pictureBlobDao;
    private final VideoBlobDao videoBlobDao;

    @Autowired
    public ToolService(ToolTableDao toolTableDao, PictureBlobDao pictureBlobDao, VideoBlobDao videoBlobDao) {
        this.toolTableDao = toolTableDao;
        this.pictureBlobDao = pictureBlobDao;
        this.videoBlobDao = videoBlobDao;
    }

    public List<ToolEntity> getToolsById(List<String> idList) {
        List<ToolEntity> list = new ArrayList<>();
        for (String id : idList) {
            ToolEntity toolEntity = toolTableDao.queryToolById(id);
            if (toolEntity != null) {
                list.add(toolEntity);
            }
        }
        return list;
    }

    public List<ToolEntity> getAll() { return toolTableDao.queryAll(); }

    public ToolEntity insert(String name, String description, String picture){
        ToolEntity toolEntity = new ToolEntity(name, description, picture);
        /* put picture in azure blob */
        String filename = pictureBlobDao.insertPicture(picture, name + ".png", ImageType.JPEG);
        if (filename == null) {
            return null;
        }
        toolEntity.setPicture(filename);
        /* store tool entity in azure table */
        if (toolTableDao.insert(toolEntity)) {
            return toolEntity;
        }
        return null;
    }

    public boolean deleteById(String id) { return toolTableDao.deleteById(id); }

    public ToolEntity updateDescriptionById(String id, String description) {
        ToolEntity toolEntity = toolTableDao.queryToolById(id);
        toolEntity.setDescription(description);
        return toolTableDao.update(toolEntity);
    }

    public ToolEntity updatePictureById(String id, String picture) {
        ToolEntity toolEntity = toolTableDao.queryToolById(id);
        toolEntity.setDescription(picture);
        return toolTableDao.update(toolEntity);
    }

    public List<ToolEntity> searchById(String id) {
        List<ToolEntity> tools = new ArrayList<>();
        ToolEntity tool = toolTableDao.queryToolById(id);
        if (tool != null) {
            tools.add(tool);
        }
        return tools;
    }

    // todo: demo, delete
    public String uploadVideo(MultipartFile file) {
        String type = Objects.requireNonNull(file.getContentType());
        if (!type.startsWith("video")) {
            return null;
        }
        return this.videoBlobDao.insertVideo(file, type);
    }
}
