package com.ecnu.g03.pethospital.dao;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @author Juntao Peng, Jiayi Zhu
 * @date 2021/3/17 22:09
 */
@PropertySource(value = "classpath:application.yml")
public abstract class BaseTableDao {
    private final String tableName;
    protected CloudTable cloudTable;
    @Value("${azure.storage.connection-string}")
    private String connectionString;

    public BaseTableDao(String tableName) {
        this.tableName = tableName;
    }

    @PostConstruct
    protected void setCloudTableReference() {
        try {
            CloudStorageAccount cloudStorageAccount = CloudStorageAccount.parse(connectionString);
            CloudTableClient cloudTableClient = cloudStorageAccount.createCloudTableClient();
            cloudTable = cloudTableClient.getTableReference(tableName);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
