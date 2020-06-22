package com.hs.entity;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-03 17:00
 **/
@Data
public class Dingdan {
    private List <Cart>goodsList;
    private Double alltatal;
    private String formData;
}
