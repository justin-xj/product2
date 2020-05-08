package com.justin.product2;

import com.justin.product2.mybatis.entity.Employee;
import com.justin.product2.mybatis.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Product2ApplicationTests {
    @Autowired
    private EmployeeService service;

    @Test
    void contextLoads() {
        //Employee emp = service.getById(10001);
        Employee emp = new Employee();
        emp.setEmpNo(10001);
        emp.selectById();
        System.out.println(emp);
    }

}
