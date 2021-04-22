package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.blob.PictureBlobDao;
import com.ecnu.g03.pethospital.dao.blob.constant.ImageType;
import com.ecnu.g03.pethospital.dao.table.ToolTableDao;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu, Xueying Li, Shen Lei
 * @date 2021-03-29 22:14
 */
@Service
public class ToolService {

    private final ToolTableDao toolTableDao;
    private final PictureBlobDao pictureBlobDao;

    @Autowired
    public ToolService(ToolTableDao toolTableDao, PictureBlobDao pictureBlobDao) {
        this.toolTableDao = toolTableDao;
        this.pictureBlobDao = pictureBlobDao;
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
}
