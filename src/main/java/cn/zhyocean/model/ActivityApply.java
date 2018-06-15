package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 17:39
 * Describe: 活动申请
 */
@Data
public class ActivityApply {

    private int id;

    private int activityId;

    private int ybId;


    public ActivityApply() {
    }

    public ActivityApply(int activityId, int ybId) {
        this.activityId = activityId;
        this.ybId = ybId;
    }
}
