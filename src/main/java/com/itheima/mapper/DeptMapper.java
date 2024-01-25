package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     *  查询所有部门信息
     * @return
     */
    @Select("select * from dept")
    public List<Dept> list();

    /**
     * 根据ID删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     *  更新部门第一步
     * @param id
     */
    @Select("select * from dept where id=#{id}")
    Dept getById(Integer id);
    /**
     *  更新部门第二步
     * @param dept
     */
    @Update("update dept set name=#{name} where id=#{id}")
    void updateByPrimaryKey(Dept dept);

}
