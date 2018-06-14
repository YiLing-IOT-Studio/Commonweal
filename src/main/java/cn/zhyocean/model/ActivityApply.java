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

    private int activiteId;

    private int userId;

    private String applyStatus;

}
