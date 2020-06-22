package com.hs.service.impl;

import com.hs.dao.DeptDao;
import com.hs.entity.Dept;
import com.hs.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-09 22:17
 **/
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    public List<Dept> getAll() {
        return deptDao.getAll();
    }
}
