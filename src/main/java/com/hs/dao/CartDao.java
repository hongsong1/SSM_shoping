package com.hs.dao;

import com.hs.entity.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-03-15 14:46
 **/
@Repository
public interface CartDao {

    public List<Cart> getAll(Integer cid);
    /**
     * 添加到购物车
     * @param cart
     * @return
     */
    public int saveCart(Cart cart);

    /**
     * 修改数量
     * @return
     */
    public int CartCountAddOne(@Param("gid") Integer gid, @Param("cid") Integer cid);

    /**
     * 通过id进行查找看是否有该商品
     * @param gid
     * @return
     */
    public Cart findByGid(@Param("gid") Integer gid,@Param("cid") Integer cid);

    /**
     * 删除购物车
     * @param gid
     * @param cid
     * @return
     */
    Integer del(@Param("gid") Integer gid,@Param("cid") Integer cid);


}
