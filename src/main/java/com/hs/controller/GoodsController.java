package com.hs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.GoodType;
import com.hs.entity.Goods;
import com.hs.entity.QueryBody;
import com.hs.service.GoodsService;
import com.sun.deploy.util.SessionState;
import com.sun.jersey.api.client.WebResource;
import com.util.ResponseManger;
import org.junit.validator.ValidateWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sun.jersey.api.client.Client;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-13 14:20
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private Integer pageSize=20;
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("/getAll")
    public ResponseManger getAll(QueryBody queryBody){
        PageHelper.startPage(queryBody.getPageNumber(),queryBody.getPageSize());
        List<Goods> allGood = goodsService.getGoods(queryBody);
        PageInfo<Goods>pageInfo=new PageInfo<>(allGood);
        return allGood!=null&&allGood.size()>0 ?ResponseManger.success().addObject("goods",pageInfo):
                                                 ResponseManger.erro();
    }

    @RequestMapping("/getGood")
    public ResponseManger getGood(){
        List<Goods> allGood = goodsService.getAllGood();
        return  allGood!=null&&allGood.size()>0 ? ResponseManger.success().addObject("goods",allGood)
                                                :ResponseManger.erro();
    }
    @RequestMapping("/getGoodById/{id}")
    public ResponseManger getGoodById(@PathVariable Integer id){
        List<Goods> allGood = goodsService.getAllGoodById(id);
        return  allGood!=null&&allGood.size()==1 ? ResponseManger.success().addObject("goods",allGood)
                :ResponseManger.erro();
    }

    @RequestMapping("/query/{name}")
    public ResponseManger query(@PathVariable String name,Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> allGood = goodsService.queryGoods(name);
        PageInfo<Goods> goodsPageInfo = new PageInfo<Goods>(allGood);
        return  allGood!=null&&allGood.size()>0 ? ResponseManger.success().addObject("goods",goodsPageInfo)
                :ResponseManger.erro();
    }


    @RequestMapping("/save")
    public ResponseManger save(Goods goods,@RequestParam("file1") MultipartFile file1,HttpServletRequest request)throws Exception{
        Integer grade = (Integer)request.getSession().getAttribute("grade");
        if(grade>2){
            return ResponseManger.errorRole();
        }
        if(file1!=null){
       try{
           String path="http://localhost:9090/uploads/";
        String filename=file1.getOriginalFilename();
//        filename=filename.substring(filename.lastIndexOf("."),filename.length());
        if(!"".equals(filename)){
            filename=filename.substring(filename.lastIndexOf("."),filename.length());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDhhmmss");
            String format = simpleDateFormat.format(new Date());

            filename=format+filename;

            Client client = Client.create();
            WebResource resource = client.resource(path + filename);
            resource.put(file1.getBytes());
            goods.setImage(path + filename);
        }

       }catch (Exception e){
           return ResponseManger.erro();
       }
        }
        Integer update = goodsService.update(goods);
        return ResponseManger.success();
    }

    @RequestMapping("/getType")
    public ResponseManger getType(){
        List<GoodType> type = goodsService.getType();
        return type!=null&&type.size()>0 ? ResponseManger.success().addObject("types",type):ResponseManger.erro();
    }


    @RequestMapping("/del")
    public ResponseManger del(Integer state, Integer id, HttpServletRequest request){
        Integer grade = (Integer)request.getSession().getAttribute("grade");
        if(grade!=1){
            return ResponseManger.errorRole();
        }
        Integer del = goodsService.del(state, id);
        return del!=0 ?ResponseManger.success():ResponseManger.erro();
    }

}
