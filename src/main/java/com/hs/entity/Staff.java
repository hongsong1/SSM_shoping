package com.hs.entity;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-17 14:38
 **/
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 *  员工表
 * @author 大狼狗 2020-04-17
 */
@Data
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 员工id
     */
    private Integer staffid;

    /**
     * 员工姓名
     */
    private String staffname;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 员工电话
     */
    private String phone;

    /**
     * 员工身份证号码
     */
    private String idcard;

    /**
     * 员工地址
     */
    private String address;

    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT +8")
    private String createdtime;

    /**
     * 员工状态(1.在职 2.离职 3.黑名单)
     */
    private Integer status;

    /**
     * roleid
     */
    private Integer roleid;

    /**
     * 备注
     */
    private String momo;

    /**
     * enable
     */
    private Integer enable;

    private String rolename;

    public Staff() {
    }

}

