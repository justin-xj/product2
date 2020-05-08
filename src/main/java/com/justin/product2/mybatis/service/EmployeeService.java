package com.justin.product2.mybatis.service;

import com.justin.product2.mybatis.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author justin
 * @since 2020-05-03
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * 高并发接口
     * @param id
     * @return
     */
    Employee huge(Integer id);
}
