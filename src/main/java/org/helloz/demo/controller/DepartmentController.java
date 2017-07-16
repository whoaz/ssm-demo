package org.helloz.demo.controller;

import org.helloz.demo.entity.Department;
import org.helloz.demo.service.DepartmentService;
import org.helloz.demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhouk on 2017/7/16.
 */
@Controller
@RequestMapping("/dept")
public class DepartmentController {

    private @Autowired DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("getData")
    public ResultVO getData() {
        List<Department> list = departmentService.listData();
        return ResultVO.success().add("list", list);
    }

}
