package com.ecnu.g03.pethospital.controller.admin;

import com.azure.core.annotation.Get;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.department.DepartmentBase;
import com.ecnu.g03.pethospital.dto.admin.request.department.DepartmentNewRequest;
import com.ecnu.g03.pethospital.dto.admin.request.department.DepartmentUpdateRequest;
import com.ecnu.g03.pethospital.dto.admin.response.department.*;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import com.ecnu.g03.pethospital.model.parse.DepartmentDetail;
import com.ecnu.g03.pethospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/30 23:17
 */
@RestController
@RequestMapping(value = "/admin/department", produces = "application/json; charset=UTF-8")
public class DepartmentControllerM {

private final DepartmentService departmentService;

    @Autowired
    public DepartmentControllerM(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * return all departments (id and name)
     * @return {@link DepartmentGetAllResponse}
     */
    @GetMapping("/all")
    @ResponseBody
    public DepartmentGetAllResponse getAll() {
        DepartmentGetAllResponse response = new DepartmentGetAllResponse();
        List<DepartmentBase> departments = departmentService.getAll();
        if (departments.size() == 0) {
            response.setMessage("No department found");
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setMessage("Query successful");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDepartments(departments);
        return response;
    }

    @GetMapping("/vets/{did}")
    public DepartmentGetVetResponse getVets(@PathVariable("did") String did) {
        System.out.print("department id:"+ did);
        DepartmentGetVetResponse response = new DepartmentGetVetResponse();
        DepartmentDetail detail = departmentService.getVets(did);
        if (detail == null) {
            response.setMessage("No vets found");
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setMessage("Query successful");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setVets(detail);
        return response;
    }

    @GetMapping("/nurses/{did}")
    public DepartmentGetNurseResponse getNurses(@PathVariable("did") String did) {
        System.out.print("department id:"+ did);
        DepartmentGetNurseResponse response = new DepartmentGetNurseResponse();
        DepartmentDetail detail = departmentService.getNurses(did);
        if (detail == null) {
            response.setMessage("No nurses found");
            response.setStatus(ResponseStatus.NO_DATA);
            return response;
        }
        response.setMessage("Query successful");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setNurses(detail);
        return response;
    }

    @PostMapping("/new")
    public DepartmentNewResponse insertDepartment(@RequestBody DepartmentNewRequest request) {
        DepartmentNewResponse response = new DepartmentNewResponse();
        DepartmentEntity entity = departmentService.insert(request.getName());
        if (entity == null) {
            response.setMessage("Cannot add new department");
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setMessage("Insert department successfully");
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDepartment(entity);
        return response;
    }

    @GetMapping("/delete/{did}")
    public DepartmentDeleteResponse delete(@PathVariable("did") String did) {
        DepartmentDeleteResponse response = new DepartmentDeleteResponse();
        if (!departmentService.delete(did)) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            response.setSuccessful(false);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setSuccessful(true);
        return response;
    }

    @PostMapping("/nurses/update")
    public DepartmentUpdateResponse updateNurses(@RequestBody DepartmentUpdateRequest request) {
        System.out.print("department id:"+ request.getId());
        DepartmentUpdateResponse response = new DepartmentUpdateResponse();
        DepartmentEntity departmentEntity = departmentService.updateNurses(request.getId(), request.getOverview(), request.getDescription(), request.getMembers(), request.getTools());
        if (departmentEntity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDepartment(departmentEntity);
        return response;
    }

    @PostMapping("/vets/update")
    public DepartmentUpdateResponse updateVets(@RequestBody DepartmentUpdateRequest request) {
        DepartmentUpdateResponse response = new DepartmentUpdateResponse();
        DepartmentEntity departmentEntity = departmentService.updateVets(request.getId(), request.getOverview(), request.getDescription(), request.getMembers(), request.getTools());
        if (departmentEntity == null) {
            response.setStatus(ResponseStatus.DATABASE_ERROR);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setDepartment(departmentEntity);
        return response;
    }

}
