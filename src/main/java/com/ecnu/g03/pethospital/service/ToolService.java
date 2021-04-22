package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.ToolTableDao;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
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
}
