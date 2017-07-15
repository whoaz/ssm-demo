package org.helloz.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.helloz.demo.entity.Employee;
import org.helloz.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zhouk on 2017/7/15.
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController {

    private @Autowired EmployeeService employeeService;

    @RequestMapping(value = {"/", ""})
    public String list(
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Employee employee,
            ModelMap modelMap

    ) {
        PageHelper.startPage(pageNo, pageSize);
        List<Employee> employees = employeeService.listEmployee(employee);
        modelMap.put("page", new PageInfo<>(employees, 5));
        return "employee/list";
    }

}
