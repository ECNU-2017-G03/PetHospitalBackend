package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.ToolServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Jiayi Zhu
 * @date 2021-03-29 21:44
 */
@Getter
@Setter
public class ToolEntity extends BaseEntity {
    private String name;
    private String description;
    private String picture;

    public ToolEntity() { super(UUID.randomUUID().toString()); }

    public ToolEntity(String id, String name, String description, String picture) {
        super(id);
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public ToolEntity(String name, String description, String picture) {
        super(UUID.randomUUID().toString());
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public static ToolEntity fromServiceEntity(ToolServiceEntity toolServiceEntity) {
        String id = toolServiceEntity.getPartitionKey();
        String name = toolServiceEntity.getName();
        String description = toolServiceEntity.getDescription();
        String picture = toolServiceEntity.getPicture();
        return new ToolEntity(id, name, description, picture);
    }

    @Override
    public ToolServiceEntity toServiceEntity() {
        String id = getId();
        ToolServiceEntity toolServiceEntity = new ToolServiceEntity(id, id);
        toolServiceEntity.setName(name);
        toolServiceEntity.setDescription(description);
        toolServiceEntity.setPicture(picture);
        return toolServiceEntity;
    }
}
