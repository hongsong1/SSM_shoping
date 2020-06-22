package com.hs.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.entity.*;
import com.hs.service.GoodsService;
import com.hs.service.OrderService;
import com.util.PayUtils;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-03 16:59
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/dingdan")
    public ResponseManger dingdan(String goodsList,Double alltatal, HttpServletRequest request){
        List<Cart> plist = null;
        try {
            plist = objectMapper.readValue(goodsList, new TypeReference<List<Cart>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Cart cart:plist){
            List<Map> maps = goodsService.panStock(cart.getGid(), cart.getGcount());
            if(maps==null||maps.size()==0){
                List<Goods> allGoodById = goodsService.getAllGoodById(cart.getGid());
                Integer stock = allGoodById.get(0).getStock();
                String name = allGoodById.get(0).getGoodname();
                return ResponseManger.error(name+"库存量为"+stock+"您购买的数量有误");
            }
        }
        Dingdan dingdan=new Dingdan();

        dingdan.setAlltatal(alltatal);
        dingdan.setGoodsList(plist);
        request.getSession().setAttribute("dingdan",dingdan);
        return dingdan!=null?ResponseManger.success():ResponseManger.erro();
    }

    @RequestMapping("/getdingdan")
    public ResponseManger getDengdan(HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        Dingdan attribute = (Dingdan)request.getSession().getAttribute("dingdan");
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String str = PayUtils.alipay("uuid", "0.01", username, "haha");
//        attribute.setFormData(str);
        return attribute!=null?ResponseManger.success().addObject("dingdan",attribute):
                                ResponseManger.erro();
    }

    @RequestMapping("/addOrder")
    public ResponseManger addOrder(HttpServletRequest request,String address){
        String username = (String)request.getSession().getAttribute("user");
        Integer cid = (Integer)request.getSession().getAttribute("cid");
        Dingdan attribute = (Dingdan)request.getSession().getAttribute("dingdan");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String uname= (String) request.getSession().getAttribute("user");
        String str = PayUtils.alipay(uuid, attribute.getAlltatal()+"", username, "建材商品");
        orderService.addOrder(attribute,uuid,cid,address,uname);
        return str!=null ? ResponseManger.success().addObject("str",str):ResponseManger.erro();
    }

    /**
     *
     * @param state
     * @param request
     * @return
     */
    @RequestMapping("/getOrder")
    public ResponseManger getOrder(Integer state,String oid,HttpServletRequest request){
        Integer cid = (Integer)request.getSession().getAttribute("cid");
        List<Orders> all = orderService.getAll(cid, state,oid);

        return all!=null&&all.size()>0 ? ResponseManger.success().addObject("orders",all):ResponseManger.erro();
    }

    @RequestMapping("/getOrders")
    public PageInfo getOrders(@RequestBody QueryBody queryBody){
        if(queryBody.getPageNumber()==null){
            queryBody.setPageNumber(1);
        }
        int i=0;
        PageHelper.startPage(queryBody.getPageNumber(),queryBody.getPageSize());
        List<Orders> aLl1 = orderService.getALl1(queryBody);
        PageInfo<Orders>pageInfo=new PageInfo<>(aLl1);
        return aLl1!=null&&aLl1.size()>0? pageInfo:null;
    }

    /**
     * 判断用户是否存在未付的单子
     */
    @RequestMapping("/countOrder")
    public ResponseManger countOrder(HttpSession session){
        Object cid = session.getAttribute("cid");
        List<Orders> all = orderService.getAll((Integer) cid, 0,null);
        return all!=null&&all.size()>0 ? ResponseManger.erro() :ResponseManger.success();
    }

    @RequestMapping("/delOrder/{oid}")
    public String del(HttpServletResponse response, @PathVariable String oid) throws IOException {
        System.out.println(oid);
        Integer del = orderService.del(oid);
        response.sendRedirect("/dingdancenter.html?status=0");
        return null;
    }

    /**
     * 继续支付订单
     */
    @RequestMapping("/continueOrder/{oid}/{count}")
    public String continueOrder(@PathVariable String oid,@PathVariable String count,HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("user");
        String str = PayUtils.alipay(oid, count, username, "");
        return str;
    }
    @RequestMapping("/updateState/{state}/{oid}")
    public Integer updateState(@PathVariable String oid,@PathVariable Integer state,HttpServletResponse response) throws IOException {
        if(state==4){
            response.sendRedirect("/dingdancenter.html?status=1");
        }
        return orderService.updateState(oid,state);

    }

}
