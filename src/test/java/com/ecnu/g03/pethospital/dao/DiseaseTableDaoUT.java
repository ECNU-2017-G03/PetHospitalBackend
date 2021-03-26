package com.ecnu.g03.pethospital.dao;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/26 21:51
 */

@Disabled
class DiseaseTableDaoUT {
    private final String connectionString = "connectionString";
    private final String tableName = "Disease";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

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
            // when(cloudTable.execute(any(TableOperation.class))).thenReturn()
        } catch (Exception ex) {
            // Test code should not reach here
            ex.printStackTrace(System.err);
        }
    }
}