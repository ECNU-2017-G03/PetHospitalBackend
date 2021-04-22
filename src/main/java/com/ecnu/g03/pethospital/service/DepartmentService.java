package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.table.DepartmentTableDao;
import com.ecnu.g03.pethospital.dao.table.ToolTableDao;
import com.ecnu.g03.pethospital.dto.response.department.DepartmentDetailResponse;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import com.ecnu.g03.pethospital.model.parse.DepartmentDetail;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:49
 */
@Service
public class DepartmentService {
    private final DepartmentTableDao departmentTableDao;
    private final ToolTableDao toolTableDao;

    @Autowired
    public DepartmentService(DepartmentTableDao departmentTableDao, ToolTableDao toolTableDao) {
        this.departmentTableDao = departmentTableDao;
        this.toolTableDao = toolTableDao;
    }

    /**
     * Get all department names
     *
     * @return department name list
     */
    public List<DepartmentServiceEntity> getDepartmentList() {
        return departmentTableDao.queryAllDepartmentNameAndId();
    }

    /**
     * Get department detail by id and actor
     *
     * @param id    department id
     * @param actor user chosen actor
     * @return department detail
     */
    public DepartmentDetailResponse getDepartmentDetail(String id, String actor) {
        try {
            DepartmentEntity departmentEntity = departmentTableDao.queryDepartmentDetailByIdAndActor(id, actor);
            if (departmentEntity == null) {
                return null;
            }

            DepartmentDetail detail;
            if (actor.equals("vet")) {
                detail = departmentEntity.getVetDetail();
            } else {
                detail = departmentEntity.getNurseDetail();
            }

            List<ToolEntity> toolList = new ArrayList<>();
            for (String toolId : detail.getTools()) {
                ToolEntity toolEntity = toolTableDao.queryToolById(toolId);
                if (toolEntity != null) {
                    toolList.add(toolEntity);
                }
            }

            return new DepartmentDetailResponse(detail, toolList, departmentEntity.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<DepartmentEntity> getAll() {
        return departmentTableDao.queryAll();
    }

//    public DepartmentEntity insert(String name, String description) {
//        DepartmentEntity departmentEntity = new DepartmentEntity(name, description);
//        if (departmentTableDao.insert(departmentEntity)) {
//            return departmentEntity;
//        }
//        return null;
//    }

}
