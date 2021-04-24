package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.learner.LearnerNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.learner.LearnerUpdateActorRequest;
import com.ecnu.g03.pethospital.dto.admin.response.learner.*;
import com.ecnu.g03.pethospital.model.entity.UserEntity;
import com.ecnu.g03.pethospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:09
 */
@RestController
@RequestMapping(value = "/admin/learner", produces = "application/json; charset=UTF-8")
public class LearnerControllerM {

    private final UserService userService;

    @Autowired
    LearnerControllerM(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/update/actors")
    public LearnerUpdateActorResponse updateActors(@RequestBody LearnerUpdateActorRequest request) {
        LearnerUpdateActorResponse response = new LearnerUpdateActorResponse();
        UserEntity user = userService.updateActors(request.getId(), request.getActor());
        if (user == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setLearner(user);
        return response;
    }

    @PostMapping("/new")
    public LearnerNewResponse insert(@RequestBody LearnerNewRequest request) {
        LearnerNewResponse response = new LearnerNewResponse();
        String name = request.getName();
        String password = request.getPassword();
        // invalid request
        if (name == null || name.length() == 0 || password == null || password.length() == 0) {
            response.setStatus(ResponseStatus.BAD_REQUEST);
            return response;
        }
        // conflict
        if (userService.searchByNameAccurate(name) != null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            response.setMessage("User name exists");
            return response;
        }
        UserEntity user = userService.addUserByAdmin(request.getName(), request.getPassword(), request.getActor());
        response.setStatus(ResponseStatus.SUCCESS);
        response.setUser(user);
        return response;
    }

    @GetMapping("/reset/password/{id}")
    public LearnerUpdateResponse resetLearnerPassword(@PathVariable("id") String id) {
        LearnerUpdateResponse response = new LearnerUpdateResponse();
        UserEntity user = userService.resetPassword(id);
        if (user == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setUser(user);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/all")
    public LearnerGetAllResponse getAll() {
        LearnerGetAllResponse response = new LearnerGetAllResponse();
        List<UserEntity> users = userService.getAll();
        if (users.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setUsers(users);
        return response;
    }

    @GetMapping("/delete/{id}")
    public LearnerDeleteResponse delete(@PathVariable("id") String id) {
        LearnerDeleteResponse response = new LearnerDeleteResponse();
        boolean isSuccessful = userService.deleteById(id);
        if (!isSuccessful) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setSuccessful(true);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/search/{id}")
    public LearnerSearchResponse searchById(@PathVariable("id") String id) {
        LearnerSearchResponse response = new LearnerSearchResponse();
        List<UserEntity> users = userService.findByNameOrIdVague(id);
        if (users.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setUsers(users);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

}
