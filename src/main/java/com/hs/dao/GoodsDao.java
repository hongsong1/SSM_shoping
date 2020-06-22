package com.hs.dao;

import com.hs.entity.GoodType;
import com.hs.entity.Goods;
import com.hs.entity.QueryBody;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: 彭于晏
 * @create: 2020-03-12 22:51
 **/
@Repository
public interface GoodsDao {
    /**
     * 这个时后端用于查询
     * @return
     */
    public List<Goods> getGoods (QueryBody queryBody);
    /**
     * 查询所有商品 倒叙
     * @return
     */
    public List<Goods> getAllGood();

    /**
     * 通过商品类型查询
     */
    public List<Goods> getAllGoodById(Integer id);

    /**
     * 查询方法
     * @param name
     * @return
     */
    public List<Goods> queryGoods(@Param("goodname") String name);


    Integer save(Goods goods);

    Integer update(Goods goods);

    List<GoodType>gettype();

    Integer del(@Param("state") Integer state ,@Param("id") Integer id);


    Integer updateStock(@Param("id") Integer gid,@Param("stock") Integer stock);


    List<Map>panStock(@Param("id") Integer gid,@Param("stock") Integer stock);

}
