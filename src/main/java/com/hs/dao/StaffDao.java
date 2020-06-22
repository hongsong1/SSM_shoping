package com.hs.dao;

import com.hs.entity.QueryBody;
import com.hs.entity.Role;
import com.hs.entity.Staff;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface StaffDao {
    /**
     * 根据账户密码登陆
     * @param staffId
     * @param password
     * @return
     */
    List<Map> login(@Param("staffId") Integer staffId, @Param("password") String password);

    List<Staff>getAll(QueryBody query);

    Integer getStaffId();

    Integer del(@Param("status") Integer status,@Param("id") Integer id);

    Integer save(Staff staff);

    Integer update(Staff staff);
    Integer getCount();

    List<Role>getRole();

}
