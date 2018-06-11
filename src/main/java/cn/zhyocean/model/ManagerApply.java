package cn.zhyocean.model;

import lombok.Data;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 15:39
 * Describe: 管理员申请
 */
@Data
public class ManagerApply {

    private int id;

    private String applyName;

    private String superior;

    private String evidence;

    private String proposer;

    private int status;

    public ManagerApply() {
    }

    public ManagerApply(String applyName, String superior, String evidence, String proposer, int status) {
        this.applyName = applyName;
        this.superior = superior;
        this.evidence = evidence;
        this.proposer = proposer;
        this.status = status;
    }
}
