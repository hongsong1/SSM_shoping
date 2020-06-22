package com.hs.controller;

import com.hs.entity.Address;
import com.hs.service.AddressService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-31 11:01
 **/
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @RequestMapping("/addressInfo")
    public ResponseManger addressInfo(String addressInfo,HttpServletRequest request){
        request.getSession().setAttribute("addressInfo",addressInfo);
        return addressInfo!=null?ResponseManger.success():ResponseManger.erro();
    }
    @RequestMapping("/getressInfo")
    public ResponseManger getressInfo(HttpServletRequest request){
        Object addressInfo = request.getSession().getAttribute("addressInfo");
        return addressInfo!=null?ResponseManger.success().addObject("addressInfo",addressInfo):ResponseManger.erro();
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getAddress")
    public ResponseManger getAddress(HttpServletRequest request, HttpServletResponse response){
        Integer cid=(Integer)request.getSession().getAttribute("cid");
        List<Address> all = addressService.getAll(cid);
        if(all==null || all.size()==0){
            return ResponseManger.erro();
        }
        Address addresses = addressService.getAll(cid).get(0);
        return addresses!=null?
                ResponseManger.success().addObject("address",addresses):ResponseManger.erro();
    }

    /**
     *
     * @param address
     * @param request
     * @return
     */
    @RequestMapping("/updateAddress")
    public ResponseManger addAddress(Address address, HttpServletRequest request){
        Integer cid =(Integer) request.getSession().getAttribute("cid");
        address.setCid(cid);
        int add = addressService.update(address);
        return add>0?ResponseManger.success():ResponseManger.erro();
    }
}
