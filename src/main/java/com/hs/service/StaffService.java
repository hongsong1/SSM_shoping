package com.hs.service;


import com.hs.entity.Menu;
import com.hs.entity.QueryBody;
import com.hs.entity.Role;
import com.hs.entity.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface StaffService {
    /**
     * 根据账户密码登陆
     * @param staffId
     * @param password
     * @return
     */
    List<Map> login(Integer staffId, String password);

    /**
     * 获取菜单
     */
    List<Menu> getOne(Integer staffId);

    List<Staff>getAll(QueryBody query);

    Integer del(Integer status,Integer staffId);

    Integer update(Staff staff);

    List<Role> getRole();
}
