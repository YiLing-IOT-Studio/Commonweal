package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 11:23
 * Describe:
 */
@Data
public class YBUser {

    private long id;

    /**
     * 易班用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 性别
     */
    private String sex;

    /**
     * 持有网薪
     */
    private String money;

    /**
     * 所在学校id
     */
    private String schoolId;

    /**
     * 所在学校名称
     */
    private String schoolName;

    public YBUser() {
    }

    public YBUser(String userId, String username, String userNick, String sex, String money, String schoolId, String schoolName) {
        this.userId = userId;
        this.username = username;
        this.userNick = userNick;
        this.sex = sex;
        this.money = money;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }
}
