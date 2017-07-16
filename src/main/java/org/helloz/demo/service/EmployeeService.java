package org.helloz.demo.service;

import org.apache.commons.lang3.StringUtils;
import org.helloz.demo.dao.EmployeeMapper;
import org.helloz.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouk on 2017/7/15.
 */
@Service
public class EmployeeService {


    private @Autowired EmployeeMapper employeeMapper;

    public List<Employee> listEmployee(Employee employee) {
        return employeeMapper.selectByPage(employee);

    }

    public void saveEmployee(Employee form) {
        if (form.getId() != null) {
            employeeMapper.updateByPrimaryKey(form);
        }else{
            employeeMapper.insert(form);
        }
    }
}
