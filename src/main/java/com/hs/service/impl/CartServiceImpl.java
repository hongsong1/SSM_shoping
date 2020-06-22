package com.hs.service.impl;

import com.hs.dao.CartDao;
import com.hs.entity.Cart;
import com.hs.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-15 14:51
 **/
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    public List<Cart> getAll(Integer cid) {
       return cartDao.getAll(cid);
    }

    public int updataCart(Cart cart) {
        int res;
        try{
            Cart byGid = cartDao.findByGid(cart.getGid(),cart.getCid());
            if(byGid!=null){
                res = cartDao.CartCountAddOne(cart.getGid(),cart.getCid());
            }else{
                res=cartDao.saveCart(cart);
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Integer del(Integer gid, Integer cid) {
        return cartDao.del(gid, cid);
    }
}
