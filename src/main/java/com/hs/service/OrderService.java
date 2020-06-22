package com.hs.service;

import com.alipay.api.domain.Car;
import com.hs.entity.Cart;
import com.hs.entity.Dingdan;
import com.hs.entity.Orders;
import com.hs.entity.QueryBody;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-04-06 21:08
 **/
public interface OrderService {
    /**
     * @param dingdan 这个是前端封装的购物信息
     * @param oid 这个是订单编号
     * @return
     */
    int addOrder(Dingdan dingdan, String oid,Integer uid,String address,String uname);

    List<Orders>getAll(Integer cid,Integer state,String oid);
    int success(String oid);

    List <Orders>getALl1(QueryBody queryBody);

    Integer del(String oid);

    Integer updateState(String oid,Integer state);
}
