package com.hs.controller;

import com.hs.entity.Menu;
import com.hs.service.StaffService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-12 21:25
 **/

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private StaffService staffService;
    @RequestMapping("/getAdmin")
    public ResponseManger getAdmin(Integer userName, String password, HttpServletRequest request){
        List<Map> login = staffService.login(userName, password);
        if(login!=null&&login.size()>0){
            Object grade = login.get(0).get("grade");
            request.getSession().setAttribute("grade",grade);
        }
        return  login!=null&&login.size()!=0 ? ResponseManger.success().addObject("data",login.get(0)):ResponseManger.erro();
    }

    @RequestMapping("/getOneMenu")
    public ResponseManger getOne(Integer staffId){
        List<Menu> menus=staffService.getOne(staffId);
        System.out.println(menus);
        return menus!=null&&menus.size()!=0 ? ResponseManger.success().addObject("menus",menus):ResponseManger.erro();
    }


}
