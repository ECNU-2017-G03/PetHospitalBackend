package com.ecnu.g03.pethospital.dao;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Shen Lei
 * @Date 2021/3/29 8:55
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminTableDaoTest {

    private AdminTableDao adminTableDao;

    @BeforeAll
    public void setupAdminTableDao() {
       adminTableDao = new AdminTableDao();
    }

    @Test
    public void testQueryByNameAndPassword() {
        AdminEntity adminEntity = adminTableDao.queryByNameAndPassword("shen", "123456");
        assertEquals("shen", adminEntity.getName());
        assertEquals("123456", adminEntity.getPassword());
        assertEquals("a2aeb436-9d5c-4803-af78-4046af425414", adminEntity.getId());
        assertEquals(AdminRole.SUPER, adminEntity.getRole());
    }

    /* this test depends on the real-time status of our db */
    // @Test
    // public void testQueryAll() {
    //     List<AdminEntity> adminEntities = adminTableDao.queryAll();
    //     assertEquals(adminEntities.size(), 3);
    // }

}
