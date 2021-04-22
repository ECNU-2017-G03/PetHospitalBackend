package com.ecnu.g03.pethospital.dao.blob;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * @author Juntao Peng
 * @date 2021/4/22 16:58
 */
@PropertySource(value = "classpath:application.yml")
public abstract class BaseBlobDao {
    private final String containerName;
    protected BlobContainerClient blobContainerClient;
    @Value("${azure.storage.connection-string}")
    private String connectionString;

    public BaseBlobDao(String containerName) {

        this.containerName = containerName;
    }

    @PostConstruct
    protected void setCloudBlobContainerReference() {
        try {
            BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
            blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
