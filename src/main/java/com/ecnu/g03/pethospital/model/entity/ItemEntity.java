package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.ItemServiceEntity;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiayi Zhu
 * @date 2021-03-30 12:25
 */
@Getter
@Setter
public class ItemEntity extends BaseEntity {

    private String name;
    private String description;
    private int price;
    private String time;

    public ItemEntity(String id, String name, String description, int price, String time) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.time = time;
    }

    public static ItemEntity fromServiceEntity(ItemServiceEntity itemServiceEntity) {
        String id = itemServiceEntity.getPartitionKey();
        String name = itemServiceEntity.getName();
        String description = itemServiceEntity.getDescription();
        String time = itemServiceEntity.getTime();
        int price = itemServiceEntity.getPrice();
        return new ItemEntity(id, name, description, price, time);
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        ItemServiceEntity itemServiceEntity = new ItemServiceEntity(getId(), getId());
        itemServiceEntity.setDescription(description);
        itemServiceEntity.setName(name);
        itemServiceEntity.setPrice(price);
        itemServiceEntity.setTime(time);
        return itemServiceEntity;
    }

}
