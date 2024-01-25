package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@XSlf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    // private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    // 指定请求方法为GET
    @GetMapping
    public Result list() {
        log.info("查询所有的部门信息");
        List<Dept> deptList = deptService.list();
        //System.out.println("查询所有的部门信息");
        return Result.success(deptList);
    }

    /**
     * 删除部门
     *
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据id删除部门：{}", id);
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 新增部门
     *
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);
        //调用Service方法
        deptService.add(dept);
        return Result.success();

    }

    /**
     * 修改部门第一步：获取dept
     *
     * @param id
     * @return
     */
    @Log
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门第二步：更新dept
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
