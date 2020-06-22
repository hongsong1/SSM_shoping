package com.hs.controller;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-06 14:37
 **/

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hs.entity.AliReturnPayBean;
import com.hs.service.OrderService;
import com.util.PayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.avie.ltd.entity.AliReturnPayBean;
//import com.avie.ltd.service.TbPaymentRecordsService;
//import com.avie.ltd.utils.JaxbUtil;
//import com.avie.ltd.utils.PayUtil;
//import com.avie.ltd.utils.wxUtil.UnifiedOrderRespose;
//import com.thoughtworks.xstream.XStream;
//
//import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/returnPay")
public class ReturnController {

    private static Logger logger = LoggerFactory.getLogger(ReturnController.class);
    @Autowired
    private OrderService orderService;

    /**
     * 支付宝回调的接口
     *
//     * @param uuid
     * @return
     * @throws IOException
     */
    @RequestMapping("/aliReturnPay")
    public String returnPay(HttpServletRequest req,HttpServletResponse response, AliReturnPayBean returnPay)
            throws IOException {
        response.setContentType("type=text/html;charset=UTF-8");
        logger.info("****************************************支付宝的的回调函数被调用******************************");
        if (!PayUtils.checkSign(req)) {
            logger.info("****************************************验签失败*******************************************");
            response.getWriter().write("failture");
            return "error";
        }
        if (returnPay == null) {
            logger.info("支付宝的returnPay返回为空");
            response.getWriter().write("success");
            return "success";
        }
        logger.info("支付宝的returnPay" + returnPay.toString());
//        if (returnPay.getTrade_status().equals("TRADE_SUCCESS")) {
//            logger.info("支付宝的支付状态为TRADE_SUCCESS");
//            tbPaymentRecordsService.aliPaySuccess(returnPay);
//        }
        orderService.success(returnPay.getOut_trade_no());
        System.out.println(returnPay.getOut_trade_no());
        response.sendRedirect("http://localhost:8080/success.html");
        return "success";
    }
}
