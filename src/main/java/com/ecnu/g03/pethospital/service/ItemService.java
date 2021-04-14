package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.ItemTableDao;
import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu, Shen Lei
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

    public ItemEntity insert(String desc, String name, Integer price, String time) {
        ItemEntity itemEntity = new ItemEntity(desc, name, price, time);
        if (!itemTableDao.insert(itemEntity)) {
            return null;
        }
        return itemEntity;
    }

    public boolean deleteById(String id) {
        return itemTableDao.deleteById(id);
    }

    public List<ItemEntity> searchById(String id) {
        List<ItemEntity> items = new ArrayList<>();
        ItemEntity item = itemTableDao.queryItemById(id);
        if (item != null) {
            items.add(item);
        }
        return items;
    }
}
