package org.helloz.demo;

import com.github.pagehelper.PageInfo;
import org.helloz.demo.entity.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhouk on 2017/7/15.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-servlet.xml"})
public class MvcTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emp").param("pageNo", "10")).andReturn();
        PageInfo<Employee> pageInfo = (PageInfo) result.getRequest().getAttribute("page");
        long total = pageInfo.getTotal();
        System.out.println("总记录数 = " + total+"\t 总页数 ="+ pageInfo.getPages()+"\t 当前页："+pageInfo.getPageNum()+"\t 连续的页码"+ Arrays.toString(pageInfo.getNavigatepageNums()));
        List<Employee> list = pageInfo.getList();
        for (Employee employee : list) {
            System.out.println("employee = " + employee);
        }
    }

}
