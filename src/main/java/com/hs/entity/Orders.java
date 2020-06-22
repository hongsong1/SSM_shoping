package com.hs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-06 20:49
 **/
@Data
@Accessors(chain = true)
public class Orders implements Serializable {
    private String oid;
    private Integer uid;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT +8")
    private Date ordertime;
    private Double total;
    private Integer state;
    private String address;
    private String uname;
    private List<OrderItems> orderItems;
}
