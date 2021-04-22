package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.ItemTableDao;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-30 12:40
 */
@Service
public class ItemService {
    private final ItemTableDao itemTableDao;

    @Autowired
    public ItemService(ItemTableDao itemTableDao) {
        this.itemTableDao = itemTableDao;
    }

    public List<ItemEntity> getAllItems() {
        return itemTableDao.queryAllItem();
    }
}
