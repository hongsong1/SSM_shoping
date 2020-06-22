package com.hs.entity;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-15 14:37
 **/
import java.io.Serializable;
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer cid;

    /**
     * 商品id
     */
    private Integer gid;

    /**
     * 商品名称
     */
    private String gname;

    /**
     * 商品图片
     */
    private String gimg;

    /**
     * 商品价格
     */
    private Double gprice;

    /**
     * 商品数量
     */
    private Integer gcount;

    /**
     * 商品小计
     */
    private Double subtotal;

    public Cart() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public Double getGprice() {
        return gprice;
    }

    public void setGprice(Double gprice) {
        this.gprice = gprice;
    }

    public Integer getGcount() {
        return gcount;
    }

    public void setGcount(Integer gcount) {
        this.gcount = gcount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gimg='" + gimg + '\'' +
                ", gprice=" + gprice +
                ", gcount=" + gcount +
                ", subtotal=" + subtotal +
                '}';
    }
}

