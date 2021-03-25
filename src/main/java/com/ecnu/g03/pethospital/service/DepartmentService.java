package com.ecnu.g03.pethospital.service;

import com.ecnu.g03.pethospital.dao.DepartmentTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2021-03-24 22:49
 */
@Service
public class DepartmentService {
    private final DepartmentTableDao departmentTableDao;

    @Autowired
    public DepartmentService(DepartmentTableDao departmentTableDao) {
        this.departmentTableDao = departmentTableDao;
    }

    /**
     * Get all department names
     * @return department name list
     */
    public List<String> getDepartmentList() {
        return departmentTableDao.queryAllDepartmentNames();
    }

}
