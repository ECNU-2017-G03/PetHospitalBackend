package com.ecnu.g03.pethospital.model.serviceentity;

import com.microsoft.azure.storage.table.TableServiceEntity;

/**
 * @Author Juntao Peng
 * @Date 2021/3/17 22:09
 */
public class UserServiceEntity extends TableServiceEntity {
    public UserServiceEntity(String partitionKey, String rowKey) {
        this.partitionKey = partitionKey;
        this.rowKey = rowKey;
    }

    public UserServiceEntity() {
        // Blank constructor for reflection
    }

    private String name;
    private String password;
    private String actor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
