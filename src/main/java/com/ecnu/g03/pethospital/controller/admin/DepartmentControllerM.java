package com.ecnu.g03.pethospital.controller.admin;

import com.azure.core.annotation.Get;
import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.department.DepartmentBase;
import com.ecnu.g03.pethospital.dto.admin.request.department.DepartmentNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.department.DepartmentGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.department.DepartmentGetNurseResponse;
import com.ecnu.g03.pethospital.dto.admin.response.department.DepartmentGetVetResponse;
import com.ecnu.g03.pethospital.dto.admin.response.department.DepartmentNewResponse;
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

}