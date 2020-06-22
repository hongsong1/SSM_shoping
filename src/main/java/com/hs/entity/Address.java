package com.hs.entity;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-31 10:41
 **/
import lombok.Data;

import java.io.Serializable;


/**
 *  goodsaddress
 * @author 大狼狗 2020-03-31
 */
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地址编号
     */
    private Integer id;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人联系电话
     */
    private String phone;

    /**
     * 收货人电子邮箱
     */
    private String email;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String country;

    /**
     * 街道
     */
    private String street;

    /**
     * 描述
     */
    private String remark;

    /**
     * 所属用户
     */
    private Integer cid;

}

