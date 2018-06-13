package cn.zhyocean.service;

import cn.zhyocean.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/6/13 13:07
 * Describe:
 */
@Service
public interface RoleService {

    /**
     * 保存权限
     * @param role
     */
    void insertRole(Role role);

    /**
     * 通过用户名删除该id的权限
     * @param username 用户名
     */
    void deleteRoleByUsername(String username);

    /**
     * 通过用户名获得权限
     * @param username 用户名
     * @return 权限
     */
    String getRoleByUsername(String username);
}
