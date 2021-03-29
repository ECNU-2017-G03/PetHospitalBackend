package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.model.serviceentity.DiseaseCaseServiceEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;
import org.junit.jupiter.api.*;
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
class DiseaseCaseTableDaoUT {
    // Constants
    private final String connectionString = "connectionString";
    private final String tableName = "DiseaseCase";

    // IO streams for testing printStackTrace
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // Mocked dummy objects
    private static final CloudTable cloudTable = mock(CloudTable.class);
    private static DiseaseCaseTableDao diseaseCaseTableDao;

    // Toy query results
    private final DiseaseCaseServiceEntity[] diseaseCaseServiceEntities = new DiseaseCaseServiceEntity[]{
            new DiseaseCaseServiceEntity("id1", "id1", "name1", "[dis1]", "des1", "petinfo1", "[pic1]", "video1"),
            new DiseaseCaseServiceEntity("id2", "id2", "name2", "[dis2]", "des2", "petinfo2", "[pic2]", "video2")
    };

    @BeforeAll
    static void initAll() {
        diseaseCaseTableDao = new DiseaseCaseTableDao();
        ReflectionTestUtils.setField(diseaseCaseTableDao, "cloudTable", cloudTable);
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
        String actualTableName = (String) ReflectionTestUtils.getField(diseaseCaseTableDao, "tableName");
        String expectedTableName = "DiseaseCase";
        assertEquals(expectedTableName, actualTableName);
    }

    @Test
    public void testQueryDiseaseCaseByIdSuccess() {
        Iterable<DiseaseCaseServiceEntity> executionResult = Arrays.asList(diseaseCaseServiceEntities);
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseCaseServiceEntity expectedObject = diseaseCaseServiceEntities[0];
        DiseaseCaseEntity resultObject = diseaseCaseTableDao.queryDiseaseCaseById(expectedObject.getPartitionKey());

        assertEquals(expectedObject.getPartitionKey(), resultObject.getId());
        assertEquals(expectedObject.getRowKey(), resultObject.getId());
        assertEquals(expectedObject.getName(), resultObject.getName());
        assertEquals(expectedObject.getDisease(), resultObject.getDisease().toString());
        assertEquals(expectedObject.getDescription(), resultObject.getDescription());
        assertEquals(expectedObject.getPetInfo(), resultObject.getPetInfo());
        assertEquals(expectedObject.getPicture(), resultObject.getPicture().toString());
        assertEquals(expectedObject.getVideo(), resultObject.getVideo());
    }

    @Test
    public void testQueryDiseaseCaseByIdFailure() {
        Iterable<DiseaseCaseServiceEntity> executionResult = new ArrayList<>();
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseCaseEntity resultObject = diseaseCaseTableDao.queryDiseaseCaseById("dummyId");

        assertNull(resultObject);
    }

    @Test
    public void testQueryDiseaseCaseByIdException() {
        Exception expectedException = new RuntimeException("Exception content");
        when(cloudTable.execute(any(TableQuery.class))).thenThrow(expectedException);

        DiseaseCaseEntity resultObject = diseaseCaseTableDao.queryDiseaseCaseById("dummyId");

        assertNull(resultObject);
        assertTrue(errContent.toString().contains(expectedException.getMessage()));
    }

    @Test
    public void testQueryDiseaseCaseByNameSuccess() {
        Iterable<DiseaseCaseServiceEntity> executionResult = Arrays.asList(diseaseCaseServiceEntities);
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseCaseServiceEntity expectedObject = diseaseCaseServiceEntities[0];
        DiseaseCaseEntity resultObject = diseaseCaseTableDao.queryDiseaseCaseByName(expectedObject.getName());

        assertEquals(expectedObject.getPartitionKey(), resultObject.getId());
        assertEquals(expectedObject.getRowKey(), resultObject.getId());
        assertEquals(expectedObject.getName(), resultObject.getName());
        assertEquals(expectedObject.getDisease(), resultObject.getDisease().toString());
        assertEquals(expectedObject.getDescription(), resultObject.getDescription());
        assertEquals(expectedObject.getPetInfo(), resultObject.getPetInfo());
        assertEquals(expectedObject.getPicture(), resultObject.getPicture().toString());
        assertEquals(expectedObject.getVideo(), resultObject.getVideo());
    }

    @Test
    public void testQueryDiseaseCaseByNameFailure() {
        Iterable<DiseaseCaseServiceEntity> executionResult = new ArrayList<>();
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        DiseaseCaseEntity resultObject = diseaseCaseTableDao.queryDiseaseCaseByName("dummyName");

        assertNull(resultObject);
    }

    @Test
    public void testQueryDiseaseCaseByNameException() {
        Exception expectedException = new RuntimeException("Exception content");
        when(cloudTable.execute(any(TableQuery.class))).thenThrow(expectedException);

        DiseaseCaseEntity resultObject = diseaseCaseTableDao.queryDiseaseCaseByName("dummyName");

        assertNull(resultObject);
        assertTrue(errContent.toString().contains(expectedException.getMessage()));
    }

    @Test
    public void testQueryDiseaseCasesSuccess() {
        Iterable<DiseaseCaseServiceEntity> executionResult = Arrays.asList(diseaseCaseServiceEntities);
        when(cloudTable.execute(any(TableQuery.class))).thenReturn(executionResult);

        List<DiseaseCaseEntity> resultObjectList = diseaseCaseTableDao.queryDiseaseCases(5);

        for (int i = 0; i < resultObjectList.size(); i++) {
            DiseaseCaseEntity resultObject = resultObjectList.get(i);
            DiseaseCaseServiceEntity expectedObject = diseaseCaseServiceEntities[i];
            assertEquals(expectedObject.getPartitionKey(), resultObject.getId());
            assertEquals(expectedObject.getRowKey(), resultObject.getId());
            assertEquals(expectedObject.getName(), resultObject.getName());
            assertEquals(expectedObject.getDisease(), resultObject.getDisease().toString());
            assertEquals(expectedObject.getDescription(), resultObject.getDescription());
            assertEquals(expectedObject.getPetInfo(), resultObject.getPetInfo());
            assertEquals(expectedObject.getPicture(), resultObject.getPicture().toString());
            assertEquals(expectedObject.getVideo(), resultObject.getVideo());
        }
    }

    @Test
    public void testQueryDiseaseCasesException() {
        Exception expectedException = new RuntimeException("Exception content");
        when(cloudTable.execute(any(TableQuery.class))).thenThrow(expectedException);

        List<DiseaseCaseEntity> resultObjectList = diseaseCaseTableDao.queryDiseaseCases(5);

        assertNull(resultObjectList);
        assertTrue(errContent.toString().contains(expectedException.getMessage()));
    }
}