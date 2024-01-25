package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /*@GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页参数查询，参数：page={},pageSize={}",page,pageSize);
        PageBean pageBean = empServic e.page(page, pageSize);
        return Result.success(pageBean);
    }*/
    //条件分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        //记录日志
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name, gender, begin, end);
        //调用业务层分页查询功能
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        //响应
        return Result.success(pageBean);
    }


    @DeleteMapping("{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除操作，ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }


    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工, emp{}", emp);
        empService.save(emp);
        return Result.success();

    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息， id:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }


    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息， emp:{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
