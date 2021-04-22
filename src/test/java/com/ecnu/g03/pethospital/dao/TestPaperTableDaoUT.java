package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.dao.table.TestPaperTableDao;
import com.ecnu.g03.pethospital.model.entity.TestPaperEntity;
import com.ecnu.g03.pethospital.model.serviceentity.TestPaperServiceEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author ： Yiqing Tao
 * @date ：Created in 2021/3/29 8:16
 */
@ExtendWith(MockitoExtension.class)
public class TestPaperTableDaoUT {
    private static final CloudTable cloudTable = mock(CloudTable.class);
    private static TestPaperTableDao testPaperTableDao;
    private final String id = "123456";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originOut = System.out;
    private final PrintStream originErr = System.err;
    private final TestPaperServiceEntity testPaperServiceEntity = mock(TestPaperServiceEntity.class);
    private final TestPaperEntity testPaperEntity = mock(TestPaperEntity.class);
    private final TableResult tableResult = mock(TableResult.class);
    private final String filter = "filter";

    @Mock
    private TableQuery<TestPaperServiceEntity> query;

    @BeforeAll
    static void initAll() {
        testPaperTableDao = new TestPaperTableDao();
        ReflectionTestUtils.setField(testPaperTableDao, "cloudTable", cloudTable);
    }

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originOut);
        System.setErr(originErr);
    }

    @Test
    public void testQueryPaperByIdSuccess() {
        List<TestPaperServiceEntity> result = new ArrayList<>();
        result.add(testPaperServiceEntity);
        try(MockedStatic<TableQuery> mockedQuery = Mockito.mockStatic(TableQuery.class)) {
            mockedQuery.when(() -> TableQuery.generateFilterCondition(
                    "PartitionKey", TableQuery.QueryComparisons.EQUAL,
                    id)).thenReturn(filter);
            mockedQuery.when(()-> TableQuery.from(TestPaperServiceEntity.class)).thenReturn(mockedQuery);
            when(query.where(filter)).thenReturn(query);

        }

    }

}
