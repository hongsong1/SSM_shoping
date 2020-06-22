package com.hs.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-14 20:35
 **/
@Component
@Data
public class Consumer implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String qianming;
    private String sex;
    private String idcard;
    private String name;
    private String birthday;



}
