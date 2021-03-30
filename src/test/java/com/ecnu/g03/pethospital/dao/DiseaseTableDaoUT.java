package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DiseaseServiceEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Juntao Peng
 * @date Created in 2021/3/26 21:51
 */
class DiseaseTableDaoUT {
    // Constants
    private final String connectionString = "connectionString";
    private final String tableName = "Disease";

    // IO streams for testing printStackTrace
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // Mocked dummy objects
    private static final CloudTable cloudTable = mock(CloudTable.class);
    private static DiseaseTableDao diseaseTableDao;

    // Toy query results
    private final DiseaseServiceEntity[] diseaseServiceEntities = new DiseaseServiceEntity[]{
            new DiseaseServiceEntity("id1", "id1", "name1", "des1"),
            new DiseaseServiceEntity("id2", "id2", "name2", "des2")
    };

    @BeforeAll
    static void initAll() {
        diseaseTableDao = new DiseaseTableDao();
        ReflectionTestUtils.setField(diseaseTableDao, "cloudTable", cloudTable);
    }

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
    public void testConstructor() {
        String actualTableName = (String) ReflectionTestUtils.getField(diseaseTableDao, "tableName");
        String expectedTableName = "Disease";
        assertEquals(expectedTableName, actualTableName);
    }

    @Test
    public void testQueryDiseaseByIdSuccess() {
        Iterable<DiseaseServiceEntity> executionResult = Arrays.asList(diseaseServiceEntities);
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseServiceEntity expectedObject = diseaseServiceEntities[0];
        DiseaseEntity resultObject = diseaseTableDao.queryDiseaseById(expectedObject.getPartitionKey());

        assertEquals(expectedObject.getPartitionKey(), resultObject.getId());
        assertEquals(expectedObject.getRowKey(), resultObject.getId());
        assertEquals(expectedObject.getName(), resultObject.getName());
        assertEquals(expectedObject.getDescription(), resultObject.getDescription());
    }

    @Test
    public void testQueryDiseaseByIdFailure() {
        Iterable<DiseaseServiceEntity> executionResult = new ArrayList<>();
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseEntity resultObject = diseaseTableDao.queryDiseaseById("dummyId");

        assertNull(resultObject);
    }

    @Test
    public void testQueryDiseaseByIdException() {
        Exception expectedException = new RuntimeException("Exception content");
        when(cloudTable.execute(any(TableQuery.class))).thenThrow(expectedException);

        DiseaseEntity resultObject = diseaseTableDao.queryDiseaseById("dummyId");

        assertNull(resultObject);
        assertTrue(errContent.toString().contains(expectedException.getMessage()));
    }

    @Test
    public void testQueryDiseaseByNameSuccess() {
        Iterable<DiseaseServiceEntity> executionResult = Arrays.asList(diseaseServiceEntities);
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseServiceEntity expectedObject = diseaseServiceEntities[0];
        DiseaseEntity resultObject = diseaseTableDao.queryDiseaseByName(expectedObject.getName());

        assertEquals(expectedObject.getPartitionKey(), resultObject.getId());
        assertEquals(expectedObject.getRowKey(), resultObject.getId());
        assertEquals(expectedObject.getName(), resultObject.getName());
        assertEquals(expectedObject.getDescription(), resultObject.getDescription());
    }

    @Test
    public void testQueryDiseaseByNameFailure() {
        Iterable<DiseaseServiceEntity> executionResult = new ArrayList<>();
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseEntity resultObject = diseaseTableDao.queryDiseaseByName("dummyName");

        assertNull(resultObject);
    }

    @Test
    public void testQueryDiseaseByNameException() {
        Exception expectedException = new RuntimeException("Exception content");
        when(cloudTable.execute(any(TableQuery.class))).thenThrow(expectedException);

        DiseaseEntity resultObject = diseaseTableDao.queryDiseaseByName("dummyName");

        assertNull(resultObject);
        assertTrue(errContent.toString().contains(expectedException.getMessage()));
    }

    @Test
    public void testQueryDiseasesSuccess() {
        Iterable<DiseaseServiceEntity> executionResult = Arrays.asList(diseaseServiceEntities);
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        List<DiseaseEntity> resultObjectList = diseaseTableDao.queryDiseases(5);

        for (int i = 0; i < resultObjectList.size(); i++) {
            DiseaseEntity resultObject = resultObjectList.get(i);
            DiseaseServiceEntity expectedObject = diseaseServiceEntities[i];
            assertEquals(expectedObject.getPartitionKey(), resultObject.getId());
            assertEquals(expectedObject.getRowKey(), resultObject.getId());
            assertEquals(expectedObject.getName(), resultObject.getName());
            assertEquals(expectedObject.getDescription(), resultObject.getDescription());
        }
    }

    @Test
    public void testQueryDiseasesException() {
        Exception expectedException = new RuntimeException("Exception content");
        when(cloudTable.execute(any(TableQuery.class))).thenThrow(expectedException);

        List<DiseaseEntity> resultObjectList = diseaseTableDao.queryDiseases(5);

        assertNull(resultObjectList);
        assertTrue(errContent.toString().contains(expectedException.getMessage()));
    }
}