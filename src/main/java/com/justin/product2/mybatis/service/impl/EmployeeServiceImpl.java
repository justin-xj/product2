package com.justin.product2.mybatis.service.impl;

import com.justin.product2.mybatis.entity.Employee;
import com.justin.product2.mybatis.mapper.EmployeeMapper;
import com.justin.product2.mybatis.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author justin
 * @since 2020-05-03
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    /**
     * 不同的对象用不同的锁
     */
    private static ConcurrentHashMap<Integer, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    private Semaphore semaphore = new Semaphore(500);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Employee huge(Integer id) {

        Employee emp = (Employee) redisTemplate.opsForValue().get("emp_id");
        if(emp != null){
            System.out.println("查询走缓存");
            return emp;
        }
        //这里用同步锁效率不高
        //synchronized (this){
        ReentrantLock lock = new ReentrantLock();
        if(lockMap.putIfAbsent(id,lock) != null){//不为空说明有
            lock = lockMap.get(id);
        }
        lock.lock();
        try {
            //双重检查
            emp = (Employee) redisTemplate.opsForValue().get("emp_id");
            if(emp != null){
                System.out.println("查询走缓存");
                return emp;
            }
            semaphore.acquire();
            emp = getById(id);
            System.out.println("查询走数据库===");
            redisTemplate.opsForValue().set("emp_id",emp);
            semaphore.release();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        //}
        return emp;
    }
}
