package com.hs.service.impl;

import com.hs.dao.ConsumerDao;
import com.hs.entity.Consumer;
import com.hs.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-14 20:48
 **/
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerDao consumerDao;
    @Autowired
    private Consumer consumer;
    public List<Consumer> getByUser(String username,String password) {
        consumer.setUsername(username);
        consumer.setPassword(password);

        List<Consumer> byUser = consumerDao.getByUser(consumer);

        return byUser!=null&&byUser.size()==1?byUser:null;
    }

    @Override
    public int save(Consumer consumer) {
        return consumerDao.save(consumer);
    }

    @Override
    public Integer getByUsername(String username) {
        return consumerDao.getByUsername(username);
    }

    @Override
    public Integer update(Consumer consumer) {
        return consumerDao.update(consumer);
    }
}
