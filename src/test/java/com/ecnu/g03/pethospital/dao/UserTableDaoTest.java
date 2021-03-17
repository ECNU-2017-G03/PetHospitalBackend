package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.model.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Juntao Peng
 * @Date 2021/3/17 22:09
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTableDaoTest {
    private UserTableDao userTableDao;

    @BeforeAll
    public void setupUserTableDao() {
        userTableDao = new UserTableDao();
    }

    @Test
    public void testQueryUserByName() {
        UserEntity userEntity = userTableDao.queryUserByName("momo");
        assertEquals(userEntity.getName(), "momo");
        assertEquals(userEntity.getId(), "1c1edf7e-8423-4938-8ebc-ddbba58a0a4c");
        assertEquals(userEntity.getPassword(), "password");
        assertArrayEquals(userEntity.getActor().toArray(), new String[] {"frontdesk", "nurse", "vet"});
    }

    @Test
    public void testInsertThenDeleteUser() {
        UserEntity userEntity = new UserEntity(
                "yueyue",
                "password",
                Arrays.asList("nurse", "vet"));
        userTableDao.insertUser(userEntity);
        UserEntity queriedUserEntity = userTableDao.queryUserByName("yueyue");
        assertEquals(queriedUserEntity.getName(), "yueyue");
        assertNotNull(queriedUserEntity.getId());
        assertEquals(queriedUserEntity.getPassword(), "password");
        assertArrayEquals(queriedUserEntity.getActor().toArray(), new String[] {"nurse", "vet"});
        userTableDao.deleteUserById(queriedUserEntity.getId());
        queriedUserEntity = userTableDao.queryUserByName("yueyue");
        assertNull(queriedUserEntity);
    }
}