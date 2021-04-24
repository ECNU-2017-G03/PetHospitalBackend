package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.UserTableDao;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.model.status.UserRegisterStatus;
import com.ecnu.g03.pethospital.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu, Shen Lei
 * @date 2021-03-23 22:45
 */
@Service
public class UserService {
    private final UserTableDao userTableDao;

    @Autowired
    public UserService(UserTableDao userTableDao) {
        this.userTableDao = userTableDao;
    }

    /**
     * Check login valid or not
     * @param name user name
     * @param password password
     * @return token
     */
    public String loginValidation(String name, String password) {
        UserEntity user = userTableDao.queryUserByNameAndPassword(name, password);
        if (user != null) {
            return JwtUtil.createJWT(user);
        }
        return null;
    }

    /**
     * Check user name unique, then add user
     * @param name user name
     * @param password password
     * @return status code, OK, ERROR, EXIST
     */
    public UserRegisterStatus addUser(String name, String password) {
        // invalid request
        if (name == null || name.length() == 0 || password == null || password.length() < 6) {
            return UserRegisterStatus.ERROR;
        }
        // conflict
        if (userTableDao.queryUserByName(name) != null) {
            return UserRegisterStatus.EXIST;
        }
        UserEntity user = new UserEntity(name, password, new ArrayList<>());
        boolean status = userTableDao.insertUser(user);
        if (!status) {
            return UserRegisterStatus.ERROR;
        }

        return UserRegisterStatus.OK;
    }

    /**
     * Get user token from id, change user password
     * @param oldPassword user current password
     * @param newPassword user new password
     * @param id user id
     * @return status
     */
    public boolean changeUserPassword(String oldPassword, String newPassword, String id) {
        UserEntity user = userTableDao.queryUserById(id);
        if (user == null) {
            return false;
        }
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }
        return userTableDao.updateUserPassword(id, newPassword);
    }

    /**
     * Get user actors
     * @param id user id
     * @return user actor list
     */
    public List<String> getUserActors(String id) {
        UserEntity user = userTableDao.queryUserById(id);
        if (user != null) {
            return user.getActor();
        } else {
            return new ArrayList<>();
        }
    }

    public UserEntity updateActors(String id, List<String> actors) {
        UserEntity user = userTableDao.queryUserById(id);
        if (user == null) {
            return null;
        }
        user.setActor(actors);
        return userTableDao.update(user);
    }

    public UserEntity addUserByAdmin(String name, String password, List<String> actor) {
        UserEntity user = new UserEntity(name, password, actor);
        if (userTableDao.insertUser(user)) {
            return user;
        }
        return null;
    }

    public UserEntity resetPassword(String id) {
        Integer random = (int) (Math.random()*10000000);
        String password = String.valueOf(random);
        UserEntity user = userTableDao.queryUserById(id);
        user.setPassword(password);
        return userTableDao.update(user);
    }

    public List<UserEntity> getAll() {
        return userTableDao.getAll();
    }

    public boolean deleteById(String id) {
        return userTableDao.deleteUserById(id);
    }

    public List<UserEntity> searchById(String id) {
        List<UserEntity> users = new ArrayList<>();
        UserEntity user = userTableDao.queryUserById(id);
        if (user != null) {
            users.add(user);
        }
        return users;
    }

    public UserEntity searchByNameAccurate(String name) {
        return userTableDao.queryUserByName(name);
    }

    public List<UserEntity> findByNameOrIdVague(String keyword) {
        return userTableDao.queryByNameOrIdVague(keyword);
    }

}
