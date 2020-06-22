package com.hs.controller;

import com.hs.entity.Dept;
import com.hs.service.DeptService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-09 22:31
 **/
@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/getAll")
    @ResponseBody
    public ResponseManger getAll(){
        List<Dept> all = deptService.getAll();
        return all!=null&&all.size()>0 ? ResponseManger.success().addObject("depts",all):ResponseManger.erro();
    }
}
