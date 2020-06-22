package com.hs.dao;

import com.hs.entity.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-14 20:34
 **/
@Repository
public interface ConsumerDao {
    /**
     * 通过用户名查找
     * @param
     * @return
     */
    public List<Consumer> getByUser(Consumer consumer);

    public int save(Consumer consumer);

    public int update(Consumer consumer);

    public Integer getByUsername(String username);



}
