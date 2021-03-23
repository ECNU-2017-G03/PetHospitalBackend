package com.ecnu.g03.pethospital.dao;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Juntao Peng
 * @date 2021/3/17 22:09
 */
@Component
@PropertySource(value = "classpath:application.yml")
public class BaseTableDao {
    @Value("${azure.storage.connection-string}")
    private String connectionString;

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
