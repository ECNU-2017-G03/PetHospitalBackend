package com.ecnu.g03.pethospital.controller.admin;

import com.ecnu.g03.pethospital.constant.ResponseStatus;
import com.ecnu.g03.pethospital.dto.admin.request.department.DepartmentNewRequest;
import com.ecnu.g03.pethospital.dto.admin.response.department.DepartmentGetAllResponse;
import com.ecnu.g03.pethospital.dto.admin.response.department.DepartmentNewResponse;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import com.ecnu.g03.pethospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shen Lei
 * @date 2021/3/30 23:17
 */
@RestController
@RequestMapping("/admin/department")
public class DepartmentControllerM {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentControllerM(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public DepartmentGetAllResponse getAll() {
        DepartmentGetAllResponse response = new DepartmentGetAllResponse();
        List<DepartmentEntity> departments = departmentService.getAll();
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

    @PostMapping("/new")
    public DepartmentNewResponse insertDepartment(@RequestBody DepartmentNewRequest request) {
        DepartmentNewResponse response = new DepartmentNewResponse();
        DepartmentEntity entity = departmentService.insert(request.getName(), request.getDescription());
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
