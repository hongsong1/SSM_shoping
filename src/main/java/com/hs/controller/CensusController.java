package com.hs.controller;

import com.hs.service.CenesusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-05-11 21:40
 **/
@RestController
@RequestMapping("census")
public class CensusController {
    @Autowired
    private CenesusService cenesusService;

    @RequestMapping("getOneYearOrder")
    public Map getOneYearOrder(){
        return cenesusService.getOneNewOrder();
    }

    @RequestMapping("getOneNewType")
    public List getOneNewType(){
        return cenesusService.getOneNewType();
    }

}
