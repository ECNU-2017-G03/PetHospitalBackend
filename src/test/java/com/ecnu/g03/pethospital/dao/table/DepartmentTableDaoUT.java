package com.ecnu.g03.pethospital.dao.table;

import com.ecnu.g03.pethospital.dao.table.DepartmentTableDao;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableQuery;
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
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Jiayi Zhu
 * @date 2021-03-28 21:18
 */
@ExtendWith(MockitoExtension.class)
public class DepartmentTableDaoUT {
    private static final CloudTable cloudTable = mock(CloudTable.class);
    private static DepartmentTableDao departmentTableDao;
    private final String name = "name";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @Mock
    private DepartmentServiceEntity departmentServiceEntity;
    @Mock
    private TableQuery<DepartmentServiceEntity> query;

    @BeforeAll
    static void initAll() {
        departmentTableDao = new DepartmentTableDao();
        ReflectionTestUtils.setField(departmentTableDao, "cloudTable", cloudTable);
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
        String tableName = (String) ReflectionTestUtils.getField(departmentTableDao, "tableName");
        String realTableName = "Department";
        assertEquals(realTableName, tableName);
    }

    @Test
    public void testQueryAllDepartmentNamesSuccess() {
        String[] columns = new String[]{"Name"};
        List<DepartmentServiceEntity> result = Arrays.asList(departmentServiceEntity, departmentServiceEntity);
        List<String> expectResult = Arrays.asList(name, name);

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            dummyTableQuery.when(() -> TableQuery.from(DepartmentServiceEntity.class))
                    .thenReturn(query);
            when(query.select(columns)).thenReturn(query);
            when(cloudTable.execute(query)).thenReturn(result);
            when(departmentServiceEntity.getName()).thenReturn(name);

            // todo:
//            List<String> departmentNames = departmentTableDao.queryAllDepartmentNames();
//
//            assertThat(departmentNames, is(expectResult));
        }
    }

    @Test
    public void testQueryAllDepartmentNamesGetEmpty() {
        String[] columns = new String[]{"Name"};
        List<DepartmentServiceEntity> result = new ArrayList<>();

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            dummyTableQuery.when(() -> TableQuery.from(DepartmentServiceEntity.class))
                    .thenReturn(query);
            when(query.select(columns)).thenReturn(query);
            when(cloudTable.execute(query)).thenReturn(result);

            List<DepartmentServiceEntity> departmentNames = departmentTableDao.queryAllDepartmentNameAndId();

            assertEquals(departmentNames.size(), 0);
        }
    }
}
