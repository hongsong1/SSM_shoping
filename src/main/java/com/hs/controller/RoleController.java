package com.hs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.Role;
import com.hs.entity.TreeData;
import com.hs.entity.TreeKeys;
import com.hs.service.RoleService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-13 17:27
 **/
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/getTree")
    public List<TreeData> getTree(){
        return roleService.getTreeOne();
    }
    @RequestMapping("/getRid")
    public List<Integer>getRid(Integer roleId){
        return roleService.getResourceId(roleId);
    }

    @RequestMapping("/getRole")
    public PageInfo getRole(Integer pageNum,Integer pageSize,String typename){
        PageHelper.startPage(pageNum,pageSize);
        List<Role> all = roleService.getAll(typename);
        PageInfo<Role> pageInfo=new PageInfo<>(all);
        return pageInfo;
    }

    @RequestMapping("/setResource")
    public Integer setResource(@RequestBody TreeKeys treeKeys){
        return roleService.saveResource(treeKeys);
    }


    @RequestMapping("/updateRole")
    public Integer updateRole(@RequestBody Role role, HttpServletRequest request){
        Integer grade = (Integer)request.getSession().getAttribute("grade");
        if(grade>2){
            return 500;
        }

        return roleService.updateRole(role);
    }

    @RequestMapping("/delRole")
    public Integer delRole(Integer id,Integer status,HttpServletRequest request){
        Integer grade = (Integer)request.getSession().getAttribute("grade");
        if(grade!=1){
            return 500;
        }

        return roleService.del(id, status);
    }
}
