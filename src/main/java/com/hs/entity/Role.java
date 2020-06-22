package com.hs.entity;

/**
 * @author 彭于晏
 * @data 2019/12/26 9:45
 * @describe
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色等级
     */
    private String grade;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色状态(1启用 0禁用)
     */
    private String status;

    public Role() {
    }

}

