package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.DepartmentTableDao;
import com.ecnu.g03.pethospital.dao.ToolTableDao;
import com.ecnu.g03.pethospital.dto.response.department.DepartmentDetailResponse;
import com.ecnu.g03.pethospital.dto.admin.department.DepartmentBase;
import com.ecnu.g03.pethospital.model.entity.DepartmentEntity;
import com.ecnu.g03.pethospital.model.entity.ToolEntity;
import com.ecnu.g03.pethospital.model.parse.DepartmentDetail;
import com.ecnu.g03.pethospital.model.serviceentity.DepartmentServiceEntity;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jiayi Zhu, Shen Lei
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
     * @return department name list
     */
    public List<DepartmentServiceEntity> getDepartmentList(String actor) {
        List<DepartmentServiceEntity> list = departmentTableDao.queryAllDepartmentNameAndId();
        if (actor.equals("receptionist")) {
            List<DepartmentServiceEntity> l = new ArrayList<>();
            for (DepartmentServiceEntity department : list) {
                String name = department.getName();
                if (name.equals("前台") || name.equals("档案室")) {
                    l.add(department);
                }
            }
            list = l;
        }
        return list;
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
            } else if (actor.equals("nurse")) {
                detail = departmentEntity.getNurseDetail();
            } else {
                // receptionist
                String name = departmentEntity.getName();
                if (!name.equals("前台") && !name.equals("档案室")) {
                    return null;
                }
                detail = new DepartmentDetail(name, Arrays.asList("tom", "kate"), new ArrayList<>(), "前台工作");
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

    public List<DepartmentBase> getAll() {
        List<DepartmentEntity> departmentEntities = departmentTableDao.queryAll();
        List<DepartmentBase> result = new ArrayList<>();
        departmentEntities.forEach((d) -> {
            DepartmentBase departmentBase = new DepartmentBase();
            departmentBase.setId(d.getId());
            departmentBase.setName(d.getName());
            result.add(departmentBase);
        });
        return result;
    }

    public DepartmentDetail getVets(String id) {
        DepartmentEntity departmentEntity = departmentTableDao.queryById(id);
        if (departmentEntity == null) {
            return null;
        }
        return departmentEntity.getVetDetail();
    }

    public DepartmentDetail getNurses(String id) {
        DepartmentEntity departmentEntity = departmentTableDao.queryById(id);
        if (departmentEntity == null) {
            return null;
        }
        return departmentEntity.getNurseDetail();
    }

    public DepartmentEntity insert(String name) {
        DepartmentEntity departmentEntity = new DepartmentEntity(name);
        if (departmentTableDao.insert(departmentEntity)) {
            return departmentEntity;
        }
        return null;
    }

    public boolean delete(String id) {
        return departmentTableDao.delete(id);
    }

    public DepartmentEntity updateNurses(String id, String overview, String description, List<String> members, List<String> tools) {
        DepartmentDetail detail = new DepartmentDetail();
        detail.setDescription(description);
        detail.setMembers(members);
        detail.setOverview(overview);
        detail.setTools(tools);
        Gson gson = new Gson();
        String detailString = gson.toJson(detail);
        System.out.print("update nurses: " + detailString);
        DepartmentEntity departmentEntity = departmentTableDao.queryById(id);
        departmentEntity.setNurseDetail(detail);
        return departmentTableDao.update(departmentEntity);
    }

    public DepartmentEntity updateVets(String id, String overview, String description, List<String> members, List<String> tools) {
        DepartmentDetail detail = new DepartmentDetail();
        detail.setDescription(description);
        detail.setMembers(members);
        detail.setOverview(overview);
        detail.setTools(tools);
        Gson gson = new Gson();
        String detailString = gson.toJson(detail);
        System.out.print("update vets: " + detailString);
        DepartmentEntity departmentEntity = departmentTableDao.queryById(id);
        departmentEntity.setVetDetail(detail);
        return departmentTableDao.update(departmentEntity);
    }

}
