package com.hs.controller;

import com.hs.entity.Consumer;
import com.hs.service.ConsumerService;
import com.util.ResponseManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-14 20:56
 **/
@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    /**
     * 这个是是判断密码是否有误
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/getConsumer")
    @ResponseBody
    public ResponseManger getConsumer(String username, String password, HttpServletRequest request){
        List<Consumer> byUser = consumerService.getByUser(username, password);
        if(byUser!=null){
            request.getSession().setAttribute("users",byUser.get(0));
            request.getSession().setAttribute("user",username);
            request.getSession().setAttribute("cid",byUser.get(0).getId());

        }
        return byUser!=null?ResponseManger.success():ResponseManger.erro();
    }

    /**
     * 这个判断是否存在session为 user 属性的 值
     */
    @RequestMapping("/getSession")
    @ResponseBody
    public ResponseManger getSession(HttpServletRequest request){
        Object user = request.getSession().getAttribute("user");
        System.out.println(user);
        return user!=null ? ResponseManger.success().addObject("user",user)
                                                    :ResponseManger.erro();
    }

    /**
     * 注册
     * @param consumer
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseManger register(Consumer consumer){
       if(consumerService.getByUsername(consumer.getUsername())>0){
           return ResponseManger.erro();
       }
        int save = consumerService.save(consumer);
        return save==0 ? ResponseManger.erro():ResponseManger.success();
    }

    @RequestMapping("/destoryCid")
    @ResponseBody
    public String destpryCid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("cid");
        response.sendRedirect("http://localhost:8080/login.html");
        return "success";
    }


    @RequestMapping("/getself")
    @ResponseBody
    public ResponseManger getConsumer(HttpSession session){
        Object users = session.getAttribute("users");
        return users!=null ? ResponseManger.success().addObject("users",users) : ResponseManger.erro();
    }

    @RequestMapping("/updateself")
    @ResponseBody
    public ResponseManger updateself(Consumer consumer,HttpSession session){
        if(consumer ==null){
            return ResponseManger.erro();
        }
        String idcard = consumer.getIdcard();
        if(idcard!=null && idcard !=""){
            consumer.setBirthday(idcard.substring(6,10)+"-"+idcard.substring(10,12)+"-"+idcard.substring(12,14));
        }
        consumer.setId((Integer)session.getAttribute("cid"));
        session.setAttribute("users",consumer);
        Integer update = consumerService.update(consumer);
        return update>0 ? ResponseManger.success():ResponseManger.erro();
    }

}
