package com.hs.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-13 16:10
 **/
@Data
public class TreeData implements Serializable {
    private Integer id;
    private String label;
    private List<TreeData> children;
    private Integer rid;
}
