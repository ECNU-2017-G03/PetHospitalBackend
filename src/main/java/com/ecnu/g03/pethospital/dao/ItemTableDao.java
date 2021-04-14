package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import com.ecnu.g03.pethospital.model.serviceentity.ItemServiceEntity;
import com.ecnu.g03.pethospital.service.ItemService;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu, Shen Lei, Xueying Li
 * @date 2021-03-30 12:37
 */
@Repository
public class ItemTableDao extends BaseTableDao {

    public ItemTableDao() {
        super("Item");
    }

    public List<ItemEntity> queryAllItem() {
        TableQuery<ItemServiceEntity> query = TableQuery.from(ItemServiceEntity.class);

        List<ItemEntity> itemList = new ArrayList<>();
        for (ItemServiceEntity itemServiceEntity : cloudTable.execute(query)) {
            ItemEntity item = ItemEntity.fromServiceEntity(itemServiceEntity);
            itemList.add(item);
        }
        return itemList;
    }

    public boolean insert(ItemEntity item) {
        ItemServiceEntity itemServiceEntity = (ItemServiceEntity) item.toServiceEntity();
        try {
            cloudTable.execute(TableOperation.insert(itemServiceEntity));
            return true;
        } catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteById(String id) {
        try {
            ItemServiceEntity itemServiceEntity = new ItemServiceEntity(id, id);
            itemServiceEntity.setEtag("*");
            cloudTable.execute(TableOperation.delete(itemServiceEntity));
            return true;
        }catch (StorageException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ItemEntity queryItemById(String id) {
        String filter = TableQuery.generateFilterCondition(
                "PartitionKey",
                TableQuery.QueryComparisons.EQUAL,
                id);
        TableQuery<ItemServiceEntity> rangeQuery = TableQuery.from(ItemServiceEntity.class).where(filter);

        for (ItemServiceEntity itemServiceEntity : cloudTable.execute(rangeQuery)) {
            return ItemEntity.fromServiceEntity(itemServiceEntity);
        }
        return null;
    }

    public ItemEntity queryItemByName(String name) {
        String filter = TableQuery.generateFilterCondition(
                "Name",
                TableQuery.QueryComparisons.EQUAL,
                name);
        TableQuery<ItemServiceEntity> rangeQuery = TableQuery.from(ItemServiceEntity.class).where(filter);

        for (ItemServiceEntity itemServiceEntity : cloudTable.execute(rangeQuery)) {
            return ItemEntity.fromServiceEntity(itemServiceEntity);
        }
        return null;
    }

}
