package com.hs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.QueryBody;
import com.hs.entity.Role;
import com.hs.entity.Staff;
import com.hs.service.StaffService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-17 22:44
 **/
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
private StaffService staffService;
    @RequestMapping("/getAll")
public ResponseManger getAll(QueryBody query){
        PageHelper.startPage(query.getPageNumber(),query.getPageSize());
    List<Staff> all = staffService.getAll(query);
        PageInfo<Staff>staff=new PageInfo<>(all);
    return all!=null&&all.size()>0 ?ResponseManger.success().addObject("staffs",staff)
                                    :ResponseManger.erro();
}
@RequestMapping("/del")
public ResponseManger del(Integer status, Integer id, HttpServletRequest request){
    Integer grade = (Integer)request.getSession().getAttribute("grade");
    if(grade!=1){
        return ResponseManger.errorRole();
    }
    Integer del = staffService.del(status,id);
    return del!=null&&del>0 ?ResponseManger.success():ResponseManger.erro();
}

@RequestMapping("/update")
public ResponseManger update(Staff staff,HttpServletRequest request){
    Integer grade = (Integer)request.getSession().getAttribute("grade");
    if(grade>2){
        return ResponseManger.errorRole();
    }
    Integer update = staffService.update(staff);
    return update!=null&&update>0 ?ResponseManger.success():ResponseManger.erro();

}
@RequestMapping("/getRole")
public ResponseManger getRole(){
    List<Role> role = staffService.getRole();
    return role!=null&&role.size()>0 ?ResponseManger.success().addObject("roles",role):ResponseManger.erro();
}

}
