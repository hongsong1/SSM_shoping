package com.hs.dao;

import com.hs.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-04-13 21:12
 **/
@Repository
public interface RoleDao {
    @Select("select * from role where id !=1  order by id desc")
    public List<Role> getAll();

    @Update("update role set roleName=#{roleName},state=#{state}")
    public int update();

    @Delete("delete from resource_role where role_id=#{roleId}")
    public int delResource(Integer roleId);

    @Insert("insert into resource_role (resource_id,role_id) values (#{resourceId},#{roleId}) ")
    public int saveResource(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId);

    @Insert("insert into role (roleName,grade,description,status) values (#{roleName},#{grade},#{description},#{status})")
    public Integer saveRole(Role role);

    @Update("update role set roleName=#{roleName} ,grade=#{grade},description=#{description} ,status=#{status} where id=#{id}")
    Integer updateRole(Role role);

    @Update("update role set status = #{status} where id=#{id}")
    Integer del(@Param("id") Integer id,@Param("status") Integer status);


    @Select("select * from role where roleName like #{rolename}")
    List<Role>queryByName(String rolename);
}
