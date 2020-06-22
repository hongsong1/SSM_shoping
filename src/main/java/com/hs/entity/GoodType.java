package com.hs.entity;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-12 22:57
 **/
public class GoodType {
    private Integer id;
    private String name;
    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "GoodType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                '}';
    }
}
