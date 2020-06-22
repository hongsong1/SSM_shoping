package com.hs.service;

import com.hs.entity.Dept;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-09 22:15
 **/
public interface DeptService {
    /**
     * 获取所有部门
     * @return
     */
    public List<Dept> getAll();
}
