package com.hs.service.impl;

import com.hs.dao.CartDao;
import com.hs.dao.GoodsDao;
import com.hs.dao.OrderDao;
import com.hs.entity.*;
import com.hs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-06 21:09
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartDao cartDao;
    @Autowired
    private GoodsDao goodsDao;

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public int addOrder(Dingdan dingdan, String oid,Integer uid,String address,String uname) {
        Orders orders=new Orders().setState(0).setOrdertime(new Date()).setOid(oid).setTotal(dingdan.getAlltatal()).setUid(uid).setAddress(address).setUname(uname);
        orderDao.addOrder(orders);//添加orders表信息
        List<Cart> goodsList = dingdan.getGoodsList();
        for (Cart cart : goodsList) {
            OrderItems orderItems=new OrderItems().setSubtotal(cart.getGprice()*cart.getGcount()).setCount(cart.getGcount()).setOid(oid).setGid(cart.getGid());
            orderDao.addOrderitem(orderItems);

            cartDao.del(cart.getGid(),uid);

            goodsDao.updateStock(cart.getGid(),cart.getGcount());
        }

        return 1;
    }

    @Override
    public List<Orders> getAll(Integer cid, Integer state,String oid) {
        try{
            return orderDao.getAll(cid,state,oid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int success(String uid) {
        return orderDao.successState(uid);
    }

    @Override
    public List<Orders> getALl1(QueryBody queryBody) {
        return orderDao.getAll1(queryBody);
    }

    @Override
    public Integer del(String oid) {
        return orderDao.delState(oid);
    }

    @Override
    public Integer updateState(String oid, Integer state) {
        return orderDao.updateState(oid,state);
    }
}
