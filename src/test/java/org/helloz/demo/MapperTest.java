package org.helloz.demo;

import org.helloz.demo.dao.DepartmentMapper;
import org.helloz.demo.dao.EmployeeMapper;
import org.helloz.demo.entity.Department;
import org.helloz.demo.entity.Employee;
import org.helloz.demo.utils.ChineseNameGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zhouk on 2017/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class MapperTest {

    private @Autowired EmployeeMapper employeeMapper;
    private @Autowired DepartmentMapper departmentMapper;
    private @Autowired SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void testSelectByPrimaryKey() {
        Employee employee = employeeMapper.selectByPrimaryKey(2);
        System.out.println(employee);
        System.out.println(employee.getDept().getName());
        assertNotNull(employee.getDept());

    }


    @Test
    public void testInsertDepartment(){
        Department department = new Department();
        department.setName("IT");
        departmentMapper.insert(department);
        assertNotNull(department.getId());
    }

    @Test
    public void testInsertEmployee(){
        Employee emp = new Employee();
        emp.setName("张三");
        emp.setGender("F");
        emp.setEmail("zhangsan@qq.com");
        emp.setDeptid(2);
        employeeMapper.insert(emp);
        System.out.println(emp);
        assertNotNull(emp.getId());

    }


    @Test
    public void testInsertBatchEmployee() {
        String[] gender = {"F", "M"};
        String[] emailSuffix = {"163.com", "qq.com","gmail.com","live.com","126.com"};
        int[] deptids = {1, 2};
        Random random = new Random();

        EmployeeMapper mapper = sqlSessionTemplate.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 5000; i++) {
            String name = ChineseNameGenerator.getFullName();
            String sex = gender[random.nextInt(2)];
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String email = uuid.substring(0, 6) + "@"+emailSuffix[random.nextInt(5)];
            Employee e = new Employee(name, sex, email, deptids[random.nextInt(2)]);
            mapper.insertSelective(e);
        }
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = employeeMapper.selectByPrimaryKey(3);
        employee.setName("Lili");
        employee.setGender("F");
        employee.setEmail("lili@qq.com");
        employeeMapper.updateByPrimaryKey(employee);
        employee = employeeMapper.selectByPrimaryKey(3);
        assertEquals(employee.getName(), "Lili");
    }


}
