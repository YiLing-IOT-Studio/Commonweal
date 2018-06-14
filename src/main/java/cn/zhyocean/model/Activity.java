package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 16:04
 * Describe: 公益活动信息
 */
@Data
public class Activity {

    private int id;

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
     * 活动时间
     */
    private String activityDate;

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
    private String deadline;

    /**
     *人数限制
     */
    private int personalLimit;

    public Activity() {
    }


    public Activity(String title, String category, String activityDate, String msg, String place, String deadline, int personalLimit) {
        this.title = title;
        this.category = category;
        this.activityDate = activityDate;
        this.msg = msg;
        this.place = place;
        this.deadline = deadline;
        this.personalLimit = personalLimit;
    }

    public Activity(String title, String category, String publisher, String img, String publishDate, String activityDate, int remain, String msg, String place, String deadline, int personalLimit) {
        this.title = title;
        this.category = category;
        this.publisher = publisher;
        this.img = img;
        this.publishDate = publishDate;
        this.activityDate = activityDate;
        this.remain = remain;
        this.msg = msg;
        this.place = place;
        this.deadline = deadline;
        this.personalLimit = personalLimit;
    }
}
