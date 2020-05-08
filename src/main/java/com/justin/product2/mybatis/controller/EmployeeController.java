package com.justin.product2.mybatis.controller;


import com.justin.product2.mybatis.entity.Employee;
import com.justin.product2.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author justin
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/huge")
    public String huge(){
        Employee e = employeeService.huge();
        return e.toString();
    }

}
