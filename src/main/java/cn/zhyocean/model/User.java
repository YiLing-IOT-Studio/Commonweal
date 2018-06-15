package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/12 13:50
 * Describe:
 */
@Data
public class User {

    private int id;

    /**
     * 易班id
     */
    private long ybId;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 学院
     */
    private String school;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private String grade;

    /**
     * 电话
     */
    private String telephoneNumber;

}
