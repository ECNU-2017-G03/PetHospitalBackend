package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.disease.cases.DiseaseCaseNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.disease.cases.DiseaseCaseUpdateRequest;
import com.ecnu.g03.pethospital.dto.admin.response.disease.cases.*;
import com.ecnu.g03.pethospital.model.entity.DiseaseCaseEntity;
import com.ecnu.g03.pethospital.service.DiseaseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 13:08
 */
@RestController
@RequestMapping(value = "/admin/case", produces = "application/json; charset=UTF-8")
public class DiseaseCaseControllerM {

    private final DiseaseCaseService diseaseCaseService;

    /**
     * retrieve disease cases whose name or id matches keyword
     * @param keyword id or name
     * @return {@link DiseaseCaseSearchResponse}
     */
    @GetMapping("/search/{keyword}")
    public DiseaseCaseSearchResponse search(@PathVariable("keyword") String keyword) {
        DiseaseCaseSearchResponse response = new DiseaseCaseSearchResponse();
        List<DiseaseCaseEntity> diseaseCaseEntities = diseaseCaseService.findByIdOrDescOrName(keyword);
        if (diseaseCaseEntities.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setDiseaseCases(diseaseCaseEntities);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    /**
     * retrieve detail of a single disease case
     * @param keyword id
     * @return {@link DiseaseCaseDetailResponse}
     */
    @GetMapping("/detail/{keyword}")
    public DiseaseCaseDetailResponse findById(@PathVariable("keyword") String keyword) {
        DiseaseCaseDetailResponse response = new DiseaseCaseDetailResponse();
        DiseaseCaseEntity diseaseCaseEntities = diseaseCaseService.findById(keyword);
        if (diseaseCaseEntities == null) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setDiseaseCase(diseaseCaseEntities);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

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
       if (!diseaseCaseService.deleteById(id)) {
           response.setSuccessful(false);
           response.setStatus(ResponseStatus.DATABASE_ERROR);
           return response;
       }
       response.setSuccessful(true);
       response.setStatus(ResponseStatus.SUCCESS);
       return response;
    }

    @PostMapping("/new")
    public DiseaseCaseNewResponse insert(@RequestBody DiseaseCaseNewRequest request) {
        DiseaseCaseNewResponse response = new DiseaseCaseNewResponse();
        DiseaseCaseEntity entity = diseaseCaseService.insert(request.getName(), request.getDisease(),
                request.getDescription(), request.getPetInfo(), request.getPicture(), request.getVideo());
        if (entity == null) {
            response.setMessage("Cannot add new disease case");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("Insert disease case successfully");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDiseaseCase(entity);
        return response;
    }

    @PostMapping("/update")
    public DiseaseCaseUpdateResponse update(@RequestBody DiseaseCaseUpdateRequest request) {
        DiseaseCaseUpdateResponse response = new DiseaseCaseUpdateResponse();
        DiseaseCaseEntity entity = diseaseCaseService.update(request.getId(), request.getName(), request.getDisease(),
                request.getDescription(), request.getPetInfo(), request.getPicture(), request.getVideo());
        if (entity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDiseaseCase(entity);
        return response;
    }

}
