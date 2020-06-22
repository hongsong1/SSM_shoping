package com.hs.entity;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-12 22:56
 **/
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品编号
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String goodname;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品类型
     */
    private Integer typeid;

    /**
     * 商品描述
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品上加时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT +8")
    private String createtime;

    /**
     * 商品状态 下架为0 上架为1 缺货为2
     */
    private Integer state;

    private GoodsConfigure goodsConfigure;

    private String name;



    public void setGoodsConfigure(GoodsConfigure goodsConfigure) {
        this.goodsConfigure = goodsConfigure;
    }

    public GoodsConfigure getGoodsConfigure() {
        return goodsConfigure;
    }

    public Goods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer gettypeid() {
        return typeid;
    }

    public void settypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodname='" + goodname + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", typeid=" + typeid +
                ", remark='" + remark + '\'' +
                ", image='" + image + '\'' +
                ", createtime=" + createtime +
                ", state=" + state +
                ", goodsConfigure=" + goodsConfigure +
                ", name='" + name + '\'' +
                '}';
    }
}

