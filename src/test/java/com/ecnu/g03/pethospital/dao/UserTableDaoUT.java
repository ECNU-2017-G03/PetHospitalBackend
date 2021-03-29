package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.serviceentity.UserServiceEntity;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Jiayi Zhu
 * @date 2021-03-28 13:07
 */
@ExtendWith(MockitoExtension.class)
public class UserTableDaoUT {
    private static final CloudTable cloudTable = mock(CloudTable.class);
    private static UserTableDao userTableDao;
    private final String id = "123456";
    private final String password = "password";
    private final String filter = "filter";
    private final String name = "testName";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final UserServiceEntity userServiceEntity = mock(UserServiceEntity.class);
    private final UserEntity userEntity = mock(UserEntity.class);
    private final TableOperation tableOperation = mock(TableOperation.class);
    private final TableResult tableResult = mock(TableResult.class);
    private final StorageException storageException = mock(StorageException.class);
    @Mock
    private TableQuery<UserServiceEntity> query;

    @BeforeAll
    static void initAll() {
        userTableDao = new UserTableDao();
        ReflectionTestUtils.setField(userTableDao, "cloudTable", cloudTable);
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
        String tableName = (String) ReflectionTestUtils.getField(userTableDao, "tableName");
        String realTableName = "User";
        assertEquals(realTableName, tableName);
    }

    @Test
    public void testInsertUserSuccess() throws Exception {
        try (MockedStatic<TableOperation> dummyTableOperation = Mockito.mockStatic(TableOperation.class)) {
            when(userEntity.toServiceEntity()).thenReturn(userServiceEntity);
            dummyTableOperation.when(() -> TableOperation.insert(userServiceEntity))
                    .thenReturn(tableOperation);
            when(cloudTable.execute(tableOperation)).thenReturn(tableResult);

            boolean status = userTableDao.insertUser(userEntity);

            assertTrue(status);
        }
    }

    @Test
    public void testInsertUserFail() throws Exception {
        try (MockedStatic<TableOperation> dummyTableOperation = Mockito.mockStatic(TableOperation.class)) {
            when(userEntity.toServiceEntity()).thenReturn(userServiceEntity);
            dummyTableOperation.when(() -> TableOperation.insert(userServiceEntity))
                    .thenReturn(tableOperation);
            when(cloudTable.execute(tableOperation)).thenThrow(storageException);

            boolean status = userTableDao.insertUser(userEntity);

            assertFalse(status);
        }
    }

    @Test
    public void testDeleteUserByIdSuccess() throws Exception {
        try (MockedStatic<TableOperation> dummyTableOperation = Mockito.mockStatic(TableOperation.class)) {
            try (MockedConstruction<UserServiceEntity> dummyTableServiceEntity =
                         Mockito.mockConstruction(UserServiceEntity.class,
                                 (mockUserServiceEntity, context) -> {
                                     doNothing().when(mockUserServiceEntity).setEtag("*");
                                     dummyTableOperation
                                             .when(() -> TableOperation.delete(mockUserServiceEntity))
                                             .thenReturn(tableOperation);
                                 })) {
                when(cloudTable.execute(tableOperation)).thenReturn(tableResult);

                boolean status = userTableDao.deleteUserById(id);

                assertTrue(status);
            }
        }
    }

    @Test
    public void testDeleteUserByIdFail() throws Exception {
        try (MockedStatic<TableOperation> dummyTableOperation = Mockito.mockStatic(TableOperation.class)) {
            try (MockedConstruction<UserServiceEntity> dummyTableServiceEntity =
                         Mockito.mockConstruction(UserServiceEntity.class,
                                 (mockUserServiceEntity, context) -> {
                                     doNothing().when(mockUserServiceEntity).setEtag("*");
                                     dummyTableOperation
                                             .when(() -> TableOperation.delete(mockUserServiceEntity))
                                             .thenReturn(tableOperation);
                                 })) {

                when(cloudTable.execute(tableOperation)).thenThrow(storageException);

                boolean status = userTableDao.deleteUserById(id);

                assertFalse(status);
            }
        }
    }

    @Test
    public void testUpdateUserPasswordSuccess() throws Exception {
        try (MockedStatic<TableOperation> dummyTableOperation = Mockito.mockStatic(TableOperation.class)) {
            try (MockedConstruction<UserServiceEntity> dummyTableServiceEntity =
                         Mockito.mockConstruction(UserServiceEntity.class,
                                 (mockUserServiceEntity, context) -> {
                                     doNothing().when(mockUserServiceEntity).setEtag("*");
                                     doNothing().when(mockUserServiceEntity).setPassword(password);
                                     dummyTableOperation
                                             .when(() -> TableOperation.merge(mockUserServiceEntity))
                                             .thenReturn(tableOperation);
                                 })) {

                when(cloudTable.execute(tableOperation)).thenReturn(tableResult);

                boolean status = userTableDao.updateUserPassword(id, password);

                assertTrue(status);
            }
        }
    }

    @Test
    public void testUpdateUserPasswordFail() throws Exception {
        try (MockedStatic<TableOperation> dummyTableOperation = Mockito.mockStatic(TableOperation.class)) {
            try (MockedConstruction<UserServiceEntity> dummyTableServiceEntity =
                         Mockito.mockConstruction(UserServiceEntity.class,
                                 (mockUserServiceEntity, context) -> {
                                     doNothing().when(mockUserServiceEntity).setEtag("*");
                                     doNothing().when(mockUserServiceEntity).setPassword(password);
                                     dummyTableOperation
                                             .when(() -> TableOperation.merge(mockUserServiceEntity))
                                             .thenReturn(tableOperation);
                                 })) {

                when(cloudTable.execute(tableOperation)).thenThrow(storageException);

                boolean status = userTableDao.updateUserPassword(id, password);

                assertFalse(status);
            }
        }
    }

    @Test
    public void testQueryUserByIdSuccess() {
        List<UserServiceEntity> result = new ArrayList<>();
        result.add(userServiceEntity);

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            try (MockedStatic<UserEntity> dummyUserEntity = Mockito.mockStatic(UserEntity.class)) {
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "PartitionKey",
                                TableQuery.QueryComparisons.EQUAL,
                                id))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.from(UserServiceEntity.class)).thenReturn(query);
                when(query.where(filter)).thenReturn(query);
                when(cloudTable.execute(query)).thenReturn(result);
                dummyUserEntity.when(() -> UserEntity.fromServiceEntity(userServiceEntity)).thenReturn(userEntity);

                UserEntity userEntity = userTableDao.queryUserById(id);

                assertEquals(userEntity, this.userEntity);
            }
        }
    }

    @Test
    public void testQueryUserByIdGetNull() {
        List<UserServiceEntity> result = new ArrayList<>();

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            dummyTableQuery.when(
                    () -> TableQuery.generateFilterCondition(
                            "PartitionKey",
                            TableQuery.QueryComparisons.EQUAL,
                            id))
                    .thenReturn(filter);
            dummyTableQuery.when(() -> TableQuery.from(UserServiceEntity.class))
                    .thenReturn(query);
            when(query.where(filter)).thenReturn(query);
            when(cloudTable.execute(query)).thenReturn(result);

            UserEntity userEntity = userTableDao.queryUserById(id);

            assertNull(userEntity);
        }
    }

    @Test
    public void testQueryUserByNameSuccess() {
        List<UserServiceEntity> result = new ArrayList<>();
        result.add(userServiceEntity);

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            try (MockedStatic<UserEntity> dummyUserEntity = Mockito.mockStatic(UserEntity.class)) {
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "Name",
                                TableQuery.QueryComparisons.EQUAL,
                                name))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.from(UserServiceEntity.class))
                        .thenReturn(query);
                when(query.where(filter)).thenReturn(query);
                when(cloudTable.execute(query)).thenReturn(result);
                dummyUserEntity.when(() -> UserEntity.fromServiceEntity(userServiceEntity))
                        .thenReturn(userEntity);

                UserEntity userEntity = userTableDao.queryUserByName(name);

                assertEquals(userEntity, this.userEntity);
            }
        }
    }

    @Test
    public void testQueryUserByNameGetNull() {
        List<UserServiceEntity> result = new ArrayList<>();

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            try (MockedStatic<UserEntity> dummyUserEntity = Mockito.mockStatic(UserEntity.class)) {
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "Name",
                                TableQuery.QueryComparisons.EQUAL,
                                name))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.from(UserServiceEntity.class))
                        .thenReturn(query);
                when(query.where(filter)).thenReturn(query);
                when(cloudTable.execute(query)).thenReturn(result);
                dummyUserEntity.when(() -> UserEntity.fromServiceEntity(userServiceEntity))
                        .thenReturn(userEntity);

                UserEntity userEntity = userTableDao.queryUserByName(name);

                assertNull(userEntity);
            }
        }
    }

    @Test
    public void testQueryUserByNameAndPasswordSuccess() {
        List<UserServiceEntity> result = new ArrayList<>();
        result.add(userServiceEntity);

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            try (MockedStatic<UserEntity> dummyUserEntity = Mockito.mockStatic(UserEntity.class)) {
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "Name",
                                TableQuery.QueryComparisons.EQUAL,
                                name))
                        .thenReturn(filter);
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "Password",
                                TableQuery.QueryComparisons.EQUAL,
                                password))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.combineFilters(filter, TableQuery.Operators.AND, filter))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.from(UserServiceEntity.class))
                        .thenReturn(query);
                when(query.where(filter)).thenReturn(query);
                when(cloudTable.execute(query)).thenReturn(result);
                dummyUserEntity.when(() -> UserEntity.fromServiceEntity(userServiceEntity))
                        .thenReturn(userEntity);

                UserEntity userEntity = userTableDao.queryUserByNameAndPassword(name, password);

                assertEquals(userEntity, this.userEntity);
            }
        }
    }

    @Test
    public void testQueryUserByNameAndPasswordGetNull() {
        List<UserServiceEntity> result = new ArrayList<>();

        try (MockedStatic<TableQuery> dummyTableQuery = Mockito.mockStatic(TableQuery.class)) {
            try (MockedStatic<UserEntity> dummyUserEntity = Mockito.mockStatic(UserEntity.class)) {
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "Name",
                                TableQuery.QueryComparisons.EQUAL,
                                name))
                        .thenReturn(filter);
                dummyTableQuery.when(
                        () -> TableQuery.generateFilterCondition(
                                "Password",
                                TableQuery.QueryComparisons.EQUAL,
                                password))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.combineFilters(filter, TableQuery.Operators.AND, filter))
                        .thenReturn(filter);
                dummyTableQuery.when(() -> TableQuery.from(UserServiceEntity.class))
                        .thenReturn(query);
                when(query.where(filter)).thenReturn(query);
                when(cloudTable.execute(query)).thenReturn(result);
                dummyUserEntity.when(() -> UserEntity.fromServiceEntity(userServiceEntity))
                        .thenReturn(userEntity);

                UserEntity userEntity = userTableDao.queryUserByNameAndPassword(name, password);

                assertNull(userEntity);
            }
        }
    }
}
