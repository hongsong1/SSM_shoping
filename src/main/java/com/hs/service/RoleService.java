package com.hs.service;

import com.hs.entity.Role;
import com.hs.entity.TreeData;
import com.hs.entity.TreeKeys;

import java.util.List;

/**
 * @author: 彭于晏
 * @create: 2020-04-13 17:24
 **/
public interface RoleService {
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
    public int saveResource(TreeKeys treeKeys);

    /**
     * 获取所哟角色
     * @return
     */
    List<Role> getAll(String roleName);

    /**
     * 修改角色
     * @param role
     * @return
     */
    Integer updateRole(Role role);

    Integer del(Integer id,Integer status);


}
