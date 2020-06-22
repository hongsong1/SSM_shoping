package com.hs.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: 彭于晏
 * @create: 2020-05-11 21:26
 **/
@Repository
public interface CensusDao {

    @Select("select dd.ym ym,IFNULL(o.ctotal,0) ctotal from (select DATE_FORMAT(SUBDATE(current_date,INTERVAL month1 month),'%Y-%m') ym from monthday where month1<12 order by ym)dd left join (select DATE_FORMAT(ordertime,'%Y-%m')oym,sum(total) ctotal from orders where state!=0 and state!=2 GROUP BY DATE_FORMAT(ordertime,'%Y-%m') order by ordertime)o on dd.ym=o.oym order by dd.ym")
    List<Map<String,Object>>getOneNewOrder();

    /**
     * 进一个月各类商品销售情况
     */
    @Select("select gtype.gtname name, IFNULL(ots.subtotal,0) value from \n" +
            "(select name gtname from goods_type)gtype left join \n" +
            "(select gt.`name` gname,SUM(ot.subtotal) subtotal from orders o join" +
            " orderitem ot on o.oid=ot.oid join goods g on g.id=ot.gid join " +
            "goods_type gt on g.typeid=gt.id " +
            "where o.ordertime> DATE_FORMAT(SUBDATE(current_date,INTERVAL 12 month),'%Y-%m') " +
            "and o.state!=0 and o.state!=2 GROUP BY gt.name)ots on ots.gname=gtname")
    List<Map<String,Object>>getOneNewType();
}
