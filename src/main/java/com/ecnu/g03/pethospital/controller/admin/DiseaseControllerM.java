package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.disease.DiseaseNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.disease.DiseaseUpdateRequest;
import com.ecnu.g03.pethospital.dto.admin.response.disease.*;
import com.ecnu.g03.pethospital.model.entity.DiseaseEntity;
import com.ecnu.g03.pethospital.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei, Xueying Li
 * @date 2021/4/7 15:14
 */
@RestController
@RequestMapping(value = "/admin/disease", produces = "application/json; charset=UTF-8")
public class DiseaseControllerM {

    private final DiseaseService diseaseService;

    @Autowired
    DiseaseControllerM(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping("/new")
    public DiseaseNewResponse insert(@RequestBody DiseaseNewRequest request) {
        DiseaseNewResponse response = new DiseaseNewResponse();
        DiseaseEntity diseaseEntity = diseaseService.insert(request.getDescription(), request.getName());
        if (diseaseEntity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDisease(diseaseEntity);
        return response;
    }

    @GetMapping("/delete/{id}")
    public DiseaseDeleteResponse deleteById(@PathVariable("id") String id) {
        DiseaseDeleteResponse response = new DiseaseDeleteResponse();
        if (!diseaseService.deleteById(id)) {
            response.setSuccessful(false);
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setSuccessful(true);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }

    @GetMapping("/all")
    public DiseaseGetAllResponse getAll() {
        DiseaseGetAllResponse response = new DiseaseGetAllResponse();
        List<DiseaseEntity> diseaseEntityList = diseaseService.getAll();
        if (diseaseEntityList.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setDiseases(diseaseEntityList);
        response.setStatus(ResponseStatus.SUCCESS);
        return response;
    }


    @PostMapping("/update")
    public DiseaseUpdateResponse update(@RequestBody DiseaseUpdateRequest request) {
        DiseaseUpdateResponse response = new DiseaseUpdateResponse();
        DiseaseEntity disease = diseaseService.update(request.getId(), request.getName(), request.getDescription());
        if (disease == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDisease(disease);
        return response;
    }

    @GetMapping("/search/{id}")
    public DiseaseSearchResponse searchByIdAndName(@PathVariable("id") String id) {
        DiseaseSearchResponse response = new DiseaseSearchResponse();
        List<DiseaseEntity> diseases = diseaseService.searchById(id);
        if (diseases.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setDiseases(diseases);
        response.setStatus(ResponseStatus.SUCCESS);

        return response;
    }

    @GetMapping("/detail/{id}")
    public DiseaseDetailResponse searchById(@PathVariable("id") String id) {
        DiseaseDetailResponse response = new DiseaseDetailResponse();
        List<DiseaseEntity> diseases = diseaseService.searchById(id);
        if (diseases.size() == 0) {
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setDisease(diseases.get(0));
        response.setStatus(ResponseStatus.SUCCESS);

        return response;
    }

}
