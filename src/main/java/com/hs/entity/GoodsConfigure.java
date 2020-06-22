package com.hs.entity;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-13 21:51
 **/
public class GoodsConfigure {
    private Integer id;
    private String  info;
    private Integer goods_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    public String toString() {
        return "GoodsConfigure{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", goods_id=" + goods_id +
                '}';
    }
}
