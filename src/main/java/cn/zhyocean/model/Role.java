package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/13 12:59
 * Describe:
 */
@Data
public class Role {

    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 对应权限
     */
    private String role;

    public Role() {
    }

    public Role(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
