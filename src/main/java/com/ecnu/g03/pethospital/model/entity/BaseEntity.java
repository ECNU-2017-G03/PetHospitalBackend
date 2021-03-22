package com.ecnu.g03.pethospital.model.entity;

import com.google.gson.Gson;
import com.microsoft.azure.storage.table.TableServiceEntity;

/**
 * @Author Juntao Peng
 * @Date 2021/3/17 22:09
 */
public class BaseEntity {
    protected static final Gson gson = new Gson();
    private String id;

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TableServiceEntity toServiceEntity() {
        return new TableServiceEntity();
    }
}
