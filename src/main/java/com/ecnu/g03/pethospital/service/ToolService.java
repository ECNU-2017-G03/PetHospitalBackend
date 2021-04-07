package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.ToolTableDao;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu, Xueying Li
 * @date 2021-03-29 22:14
 */
@Service
public class ToolService {
    private final ToolTableDao toolTableDao;

    @Autowired
    public ToolService(ToolTableDao toolTableDao) {
        this.toolTableDao = toolTableDao;
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
}
