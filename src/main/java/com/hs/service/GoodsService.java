package com.hs.service;

import com.hs.entity.GoodType;
import com.hs.entity.Goods;
import com.hs.entity.QueryBody;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-13 14:17
 **/
public interface GoodsService {
/**
 * 查询进一个月新上映的商品
 */
public List<Goods> getAllGood();

    Integer del(Integer state ,Integer id);

    /**
     * 查询所有
     * @return
     */
    public List<Goods>getGoods(QueryBody queryBody);

    /**
     * 通过商品类型查询
     */
    public List<Goods> getAllGoodById(Integer id);

    /**
     * 查询
     * @param name
     * @return
     */
    public List<Goods> queryGoods(String name);

   Integer update(Goods goods);

   List<GoodType>getType();

   List<Map>panStock(Integer gid,Integer stock);
}
