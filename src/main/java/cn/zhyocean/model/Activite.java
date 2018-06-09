package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 16:04
 * Describe: 公益活动信息
 */
@Data
public class Activite {

    private String id;

    /**
     * 活动名称
     */
    private String title;

    /**
     * 活动类别
     */
    private String category;

    /**
     *发布者
     */
    private String publisher;

    /**
     *活动图片
     */
    private String img;

    /**
     *发布时间
     */
    private String publishDate;

    /**
     *剩余名额
     */
    private int remain;

    /**
     *活动介绍
     */
    private String msg;

    /**
     *活动地点
     */
    private String place;

    /**
     *截至报名时间
     */
    private String deadLine;

    /**
     *人数限制
     */
    private int limit;
}
