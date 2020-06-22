package com.hs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * 菜单
 **/
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Data
public class Menu implements Serializable {
    private String id;
    private String resourceName;
    private String url;
    private String icon;
    private String pid;
    private String sort;
    private String identity;
    private Integer staffId;
    /**
     * 定义二级菜单
     */
    private List<Menu> menuList;

}
