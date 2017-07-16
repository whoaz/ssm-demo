package org.helloz.demo.service;

import org.helloz.demo.dao.DepartmentMapper;
import org.helloz.demo.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouk on 2017/7/16.
 */
@Service
public class DepartmentService {

    private @Autowired DepartmentMapper departmentMapper;

    public List<Department> listData() {
        return departmentMapper.selectAll();
    }

}
