package com.ecnu.g03.pethospital.model.entity;

import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;
import com.google.gson.reflect.TypeToken;
import com.microsoft.azure.storage.table.TableServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * @author Juntao Peng
 * @date 2021/3/17 22:09
 */
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String name;
    private String password;
    private List<String> actor;

    public UserEntity(String name, String password, List<String> actor) {
        super(UUID.randomUUID().toString());
        this.name = name;
        this.password = password;
        this.actor = actor;
    }

    public UserEntity(String id, String name, String password) {
        super(id);
        this.name = name;
        this.password = password;
    }

    public static UserEntity fromServiceEntity(UserServiceEntity userServiceEntity) {
        UserEntity userEntity = new UserEntity(userServiceEntity.getPartitionKey(), userServiceEntity.getName(), userServiceEntity.getPassword());
        String actorJsonString = userServiceEntity.getActor();
        List<String> actor = gson.fromJson(actorJsonString, new TypeToken<List<String>>(){}.getType());
        userEntity.setActor(actor);
        return userEntity;
    }

    @Override
    public TableServiceEntity toServiceEntity() {
        UserServiceEntity userServiceEntity = new UserServiceEntity(getId(), getId());
        userServiceEntity.setName(name);
        userServiceEntity.setPassword(password);
        userServiceEntity.setActor(gson.toJson(actor));
        return userServiceEntity;
    }
}
