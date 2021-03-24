package com.ecnu.g03.pethospital.dao;


import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

import javax.annotation.PostConstruct;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
class BaseTableDaoTest {
    private CloudTable testCloudTable;

    public BaseTableDaoTest() {
        try {
            testCloudTable = new CloudTable(new URI("http://test.com/"));
        } catch (StorageException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    class TestBaseTableDao extends BaseTableDao {

        public TestBaseTableDao(String tableName) {
            super(tableName);
        }

        @Override
        @PostConstruct
        protected void setCloudTableReference() {
            this.cloudTable = testCloudTable;
        }
    }

    @Test
    public void testCloudTableReference() {
        TestBaseTableDao testBaseTableDao = new TestBaseTableDao("test");
        assertNotNull(testBaseTableDao.cloudTable);
    }
}