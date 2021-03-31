package com.ecnu.g03.pethospital.controller;

import com.ecnu.g03.pethospital.controller.enduser.UserController;
import com.ecnu.g03.pethospital.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Jiayi Zhu
 * @date 2021-03-28 16:22
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
public class UserControllerIT {
    @Autowired
    private UserController userController;
    @MockBean
    private UserService userService;
}
