package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.response.disease.cases.DiesaseCaseNewResponse;
import com.ecnu.g03.pethospital.dto.admin.response.disease.cases.DiseaseCaseDeleteResponse;
import com.ecnu.g03.pethospital.dto.admin.response.disease.cases.DiseaseCaseGetAllResponse;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.service.DiseaseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/4/7 13:08
 */
@RestController
@RequestMapping(value = "/admin/case", produces = "application/json; charset=UTF-8")
public class DiseaseCaseControllerM {

    private final DiseaseCaseService diseaseCaseService;

    @Autowired
    DiseaseCaseControllerM(DiseaseCaseService diseaseCaseService) {
        this.diseaseCaseService = diseaseCaseService;
    }

    @GetMapping("/all")
    public DiseaseCaseGetAllResponse getAll() {
        DiseaseCaseGetAllResponse response = new DiseaseCaseGetAllResponse();
        List<DiseaseCaseEntity> diseaseCaseEntityList = diseaseCaseService.getAll();
        if (diseaseCaseEntityList.size() <= 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDiseaseCases(diseaseCaseEntityList);
        return response;
    }

    @GetMapping("/delete/{id}")
    public DiseaseCaseDeleteResponse deleteById(@PathVariable("id") String id){
       DiseaseCaseDeleteResponse response = new DiseaseCaseDeleteResponse();
       if (diseaseCaseService.deleteById(id)) {
           response.setSuccessful(false);
           response.setStatus(ResponseStatus.DATABASE_ERROR);
           return response;
       }
       response.setSuccessful(true);
       response.setStatus(ResponseStatus.SUCCESS);
       return response;
    }

    @GetMapping("/new")
    public DiesaseCaseNewResponse insert() {
        return null;
    }

}
