package com.hs.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-06 20:54
 **/
@Data
@Accessors(chain = true)
public class OrderItems implements Serializable {
    private Integer itemid;
    private Integer count;
    private Double subtotal;
    private Integer gid;
    private String oid;
    private List<Goods> goods;
}
