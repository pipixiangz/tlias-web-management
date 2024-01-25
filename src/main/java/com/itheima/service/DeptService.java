package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {
    /**
     * 查询所有部门信息
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id) throws Exception;

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 修改部门第一步
     * @param id
     */
    Dept getById(Integer id);
    /**
     * 修改部门第二步
     * @param dept
     */
    void update(Dept dept);


}
