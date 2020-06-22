package com.hs.service.impl;

import com.hs.dao.GoodsDao;
import com.hs.entity.GoodType;
import com.hs.entity.Goods;
import com.hs.entity.QueryBody;
import com.hs.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-13 14:19
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    public List<Goods> getAllGood() {
        return goodsDao.getAllGood();
    }

    @Override
    public Integer del(Integer state, Integer id) {
        if(state==1){
            state=2;
        }else {
            state=1;
        }
        return goodsDao.del(state, id);
    }

    @Override
    public List<Goods> getGoods(QueryBody query) {
        return goodsDao.getGoods(query);
    }

    public List<Goods> getAllGoodById(Integer id) {
        return goodsDao.getAllGoodById(id);
    }

    public List<Goods> queryGoods(String name) {
        if("null".equals(name)){
            name=null;
        }
        return goodsDao.queryGoods(name);
    }

    @Override
    public Integer update(Goods goods) {
        if(goods.getId()!=null){
            return goodsDao.update(goods);
        }

        return goodsDao.save(goods);
    }

    @Override
    public List<GoodType> getType() {
        return goodsDao.gettype();
    }

    @Override
    public List<Map> panStock(Integer gid,Integer stock) {
        return goodsDao.panStock(gid,stock);
    }

}
