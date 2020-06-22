package com.hs.service;

import com.hs.entity.Cart;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-15 14:50
 **/
public interface CartService {
    /**
     * 获取所有购物车
     * @return
     */
    public List<Cart> getAll(Integer cid);

    /**
     * 获取所有商品
     * @param cart
     * @return
     */
    public int updataCart(Cart cart);

    Integer del(Integer gid,Integer cid);
}
