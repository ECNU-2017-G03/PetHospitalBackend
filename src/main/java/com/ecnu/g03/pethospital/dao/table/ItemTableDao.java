package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.model.entity.ItemEntity;
import com.ecnu.g03.pethospital.model.serviceentity.ItemServiceEntity;
import com.microsoft.azure.storage.table.TableQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
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

}
