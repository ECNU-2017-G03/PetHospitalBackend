package com.ecnu.g03.pethospital.dao;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTableClient;

/**
 * @Author Juntao Peng
 * @Date 2021/3/17 22:09
 */
public class BaseTableDao {
    private final String connectionString;

    BaseTableDao() {
        connectionString = System.getenv("PET_HOSPITAL_STORAGE_CONNECTION_STRING");
    }

    public CloudTableClient getTableClient() {
        try {
            CloudStorageAccount cloudStorageAccount = CloudStorageAccount.parse(connectionString);
            return cloudStorageAccount.createCloudTableClient();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
