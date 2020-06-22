package com.hs.service.impl;

import com.hs.dao.MenuDao;
import com.hs.dao.StaffDao;
import com.hs.entity.Menu;
import com.hs.entity.QueryBody;
import com.hs.entity.Role;
import com.hs.entity.Staff;

import com.hs.service.StaffService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Map> login(Integer staffId, String password){
        if (staffId == null || staffId == 0){
            throw new RuntimeException("账户不能为空!");
        }
        if (StringUtils.isBlank(password)){
            throw new RuntimeException("密码不能为空!");
        }
        return staffDao.login(staffId,password);
    }

    @Override
    public List<Menu> getOne(Integer staffId) {
        if (staffId == null || staffId == 0){
            throw new RuntimeException("账户不能为空!");
        }
        return menuDao.getMenuListOne(staffId);
    }

    @Override
    public List<Staff> getAll(QueryBody query) {
        try{
        List<Staff> all = staffDao.getAll(query);
        if(all==null&&all.size()==0){
            throw new RuntimeException("数据有误");
        }
//        map.put("staff",staffDao.getAll());
//        map.put("count",staffDao.getCount());
        return all;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("数据有误");
        }
    }

    @Override
    public Integer del(Integer status,Integer staffId) {
        if(status==1){
            status=2;
        }else {
            status=1;
        }
        return staffDao.del(status,staffId);
    }

    @Override
    public Integer update(Staff staff) {
        try {
            if (staff.getStaffid() == null) {
                Integer staffId = staffDao.getStaffId();
                staff.setStaffid(staffId + 1);
                staff.setPassword((staffId+1)+"");
                return staffDao.save(staff);
            } else {
                return staffDao.update(staff);
            }
        }catch(Exception e){
            throw new RuntimeException("数据有误");
        }
    }

    @Override
    public List<Role> getRole() {
        return staffDao.getRole();
    }
}
