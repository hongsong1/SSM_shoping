package com.hs.service;

import com.hs.entity.Consumer;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-14 20:46
 **/
public interface ConsumerService {
    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    public List<Consumer> getByUser(String username,String password);

    public int save(Consumer consumer);

    Integer getByUsername(String username);

    Integer update(Consumer consumer);
}
