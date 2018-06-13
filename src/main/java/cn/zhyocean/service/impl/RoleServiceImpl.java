package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.RoleMapper;
import cn.zhyocean.model.Role;
import cn.zhyocean.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/13 13:08
 * Describe:
 */
@Service
public class RoleServiceImpl implements RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleMapper roleMapper;

    @Override
    public void insertRole(Role role) {
        try {
            roleMapper.insertRole(role);
        } catch (Exception e){
            logger.error("保存权限失败");
        }
    }

    @Override
    public void deleteRoleByUsername(String username) {
        roleMapper.deleteRoleByUsername(username);
    }

    @Override
    public String getRoleByUsername(String username) {
        List<String> list = roleMapper.getRoleByUsername(username);
        if (list.size() == 1){
            return "ROLE_MANAGER";
        } else if(list.size() == 2){
            return "ROLE_SUPERMANAGER";
        }
        return "ROLE_USER";
    }
}
