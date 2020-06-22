package com.hs.controller;

import com.hs.entity.Cart;
import com.hs.service.CartService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @description: 购物车
 * @author: 彭于晏
 * @create: 2020-03-15 14:44
 **/
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public ResponseManger getAll(HttpServletRequest request){
        Integer cid = (Integer) request.getSession().getAttribute("cid");
        List<Cart> all = cartService.getAll(cid);
        return all!=null&&all.size()>0?ResponseManger.success().addObject("carts",all)
                                        :ResponseManger.erro();
    }

    /**
     *
     * @param cart
     * @param request
     * @return
     */
    @RequestMapping("/updateCart")
    @ResponseBody
    public ResponseManger updateCart(Cart cart, HttpServletRequest request){
        cart.setCid((Integer)request.getSession().getAttribute("cid"));
        int i = cartService.updataCart(cart);
        return i>0 ? ResponseManger.success():ResponseManger.erro();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(HttpSession session, Integer gid, HttpServletResponse response) throws IOException {
        Integer cid=(Integer)session.getAttribute("cid");
        Integer del = cartService.del(gid, cid);
        if(del!=0){
            response.sendRedirect("http://localhost:8080/gouwuche.html");
        }

        return "success";
    }


}
