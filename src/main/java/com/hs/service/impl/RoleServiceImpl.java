package com.hs.service.impl;

import com.hs.dao.MenuDao;
import com.hs.dao.RoleDao;
import com.hs.entity.Role;
import com.hs.entity.TreeData;
import com.hs.entity.TreeKeys;
import com.hs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-04-13 17:25
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<TreeData> getTreeOne() {
        return menuDao.getTreeOne();
    }

    @Override
    public List<Integer> getResourceId(Integer roleId) {
        if(roleId==null){
            throw new RuntimeException("操作错误");
        }
        return menuDao.getResourceId(roleId);
    }

    @Transactional
    @Override
    public int saveResource(TreeKeys treeKeys) {
        try {
            roleDao.delResource(treeKeys.getRoleId());

            for (Integer key : treeKeys.getKeys()) {
                roleDao.saveResource(treeKeys.getRoleId(),key);
            }

        }catch (Exception e){
            throw new RuntimeException("修改失败");
        }

        return 0;
    }

    @Override
    public List<Role> getAll(String roleName) {
        if(roleName!=null&&roleName!=""){
            roleName="%"+roleName+"%";
            return  roleDao.queryByName(roleName);
        }
        return roleDao.getAll();
    }

    @Override
    public Integer updateRole(Role role) {
        if(role.getId()!=null){
            return roleDao.updateRole(role);
        }
        return roleDao.saveRole(role);
    }

    @Override
    public Integer del(Integer id, Integer status) {
        if(status==1){
            status=0;
        }else{
            status=1;
        }
        return roleDao.del(id,status);
    }

}
