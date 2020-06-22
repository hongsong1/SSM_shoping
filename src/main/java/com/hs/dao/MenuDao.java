package com.hs.dao;

import com.hs.entity.Menu;
import com.hs.entity.TreeData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 * @date 2019/12/23
 * 菜单接口
**/
@Repository
public interface MenuDao {
    /**
     * 通过roleId 获取 菜单
     * @param roleId
     * @return
     */
    List<Menu> getMenuListOne(Integer roleId);

    /**
     * 获取tree树形
     * @return
     */
    List<TreeData>getTreeOne();

    /**
     * 获取role对相应得Tree
     * @return
     */
    List<Integer>getResourceId(Integer roleId);
}
