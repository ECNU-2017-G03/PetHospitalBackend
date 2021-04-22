package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.dao.table.BaseTableDao;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/26 21:51
 */
class BaseTableDaoUT {
    private final String connectionString = "connectionString";
    private final String tableName = "tableName";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    Throwable illegalArgumentException = new IllegalArgumentException("Invalid connection string.");

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testSetCloudTableReferenceNormal() {
        CloudStorageAccount cloudStorageAccount = mock(CloudStorageAccount.class);
        CloudTableClient cloudTableClient = mock(CloudTableClient.class);
        CloudTable cloudTable = mock(CloudTable.class);

        try (MockedStatic<CloudStorageAccount> dummy = mockStatic(CloudStorageAccount.class)) {
            dummy.when(() -> CloudStorageAccount.parse(connectionString))
                    .thenReturn(cloudStorageAccount);
            when(cloudStorageAccount.createCloudTableClient()).thenReturn(cloudTableClient);
            when(cloudTableClient.getTableReference(tableName)).thenReturn(cloudTable);


            BaseTableDaoWrapper baseTableDaoWrapper = new BaseTableDaoWrapper(tableName);
            Field connectionStringField = baseTableDaoWrapper.getClass().getSuperclass().getDeclaredField("connectionString");
            connectionStringField.setAccessible(true);
            connectionStringField.set(baseTableDaoWrapper, connectionString);
            baseTableDaoWrapper.setCloudTableReference();

            assertEquals(cloudTable, baseTableDaoWrapper.cloudTable);
        } catch (Exception ex) {
            // Test code should not reach here
            ex.printStackTrace(System.err);
        }
    }

    @Test
    public void testSetCloudTableReferenceException() {
        CloudStorageAccount cloudStorageAccount = mock(CloudStorageAccount.class);
        CloudTableClient cloudTableClient = mock(CloudTableClient.class);
        CloudTable cloudTable = mock(CloudTable.class);

        try (MockedStatic<CloudStorageAccount> dummy = mockStatic(CloudStorageAccount.class)) {
            dummy.when(() -> CloudStorageAccount.parse(connectionString))
                    .thenThrow(illegalArgumentException);
            when(cloudStorageAccount.createCloudTableClient()).thenReturn(cloudTableClient);
            when(cloudTableClient.getTableReference(tableName)).thenReturn(cloudTable);


            BaseTableDaoWrapper baseTableDaoWrapper = new BaseTableDaoWrapper(tableName);
            Field connectionStringField = baseTableDaoWrapper.getClass().getSuperclass().getDeclaredField("connectionString");
            connectionStringField.setAccessible(true);
            connectionStringField.set(baseTableDaoWrapper, connectionString);
            baseTableDaoWrapper.setCloudTableReference();

            assertNull(baseTableDaoWrapper.cloudTable);
            assertTrue(errContent.toString().contains("Invalid connection string."));
        } catch (Exception ex) {
            // Test code should not reach here
            ex.printStackTrace(System.err);
        }
    }

    class BaseTableDaoWrapper extends BaseTableDao {
        public BaseTableDaoWrapper(String tableName) {
            super(tableName);
        }
    }

}