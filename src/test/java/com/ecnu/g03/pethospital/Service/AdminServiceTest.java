package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.constant.AdminRole;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dao.table.AdminTableDao;
import com.ecnu.g03.pethospital.model.entity.AdminEntity;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Shen Lei
 * @date 2021/4/25 22:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {

    @Autowired
    AdminService adminService;

    @MockBean
    AdminTableDao adminTableDao;

    @ParameterizedTest
    @MethodSource("provideUpdatePasswordBySelfSource")
    public void testUpdatePassword(String name, String newPassword, AdminEntity oldEntity, AdminEntity newEntity) {
        when(adminTableDao.queryByName(name)).thenReturn(oldEntity);
        when(adminTableDao.update(newEntity)).thenReturn(newEntity);
        AdminEntity result = adminService.resetPassword(name, newPassword);
        if (oldEntity == null) {
            Assert.assertNull(result);
            verify(adminTableDao, times(1)).queryByName(name);
            verify(adminTableDao, times(0)).update(newEntity);
            return;
        }
        Assert.assertEquals(oldEntity.getId(), result.getId());
        Assert.assertEquals(name, result.getName());
        Assert.assertEquals(newPassword, result.getPassword());
        verify(adminTableDao, times(1)).queryByName(name);
        verify(adminTableDao, times(1)).update(newEntity);
    }

    static List<Arguments> provideUpdatePasswordBySelfSource() {
        AdminEntity oldAdmin1 = new AdminEntity("84791df0-8732-40f6-9cec-6a25b94007c4", "orange", "1111!!!Qa", AdminRole.SUPER);
        AdminEntity newAdmin1 = new AdminEntity("84791df0-8732-40f6-9cec-6a25b94007c4", "orange", "12345Aa!", AdminRole.SUPER);
        AdminEntity oldAdmin2 = null;
        AdminEntity newAdmin2 = null;
        return Arrays.asList(
                Arguments.of("orange", "12345Aa!", oldAdmin1, newAdmin1),
                Arguments.of("一个不存在的用户名", "123456", oldAdmin2, newAdmin2));

    }
}
