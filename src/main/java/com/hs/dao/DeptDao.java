package com.hs.dao;

import com.hs.entity.Dept;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-09 22:14
 **/
@Repository
public interface DeptDao {

    public List<Dept> getAll();
}
