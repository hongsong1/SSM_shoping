package com.hs.dao;

import com.hs.entity.OrderItems;
import com.hs.entity.Orders;
import com.hs.entity.QueryBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-04-06 21:14
 **/
@Repository
public interface OrderDao {
    int addOrder(Orders orders);
    int addOrderitem(OrderItems orderItems);

    List<Orders>getAll(@Param("uid") Integer uid, @Param("state") Integer state,@Param("oid") String oid);

    int successState(String oid);

    List<Orders>getAll1(QueryBody queryBody);

    /**
     * 用户删除订单
     * @param oid
     * @return
     */
    int delState(String oid);

    /**
     * 修改订单得状态
     * @param oid
     * @param state
     * @return
     */
    int updateState(@Param("oid") String oid,@Param("state") Integer state);



}
