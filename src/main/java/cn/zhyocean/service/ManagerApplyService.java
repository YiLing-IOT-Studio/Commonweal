package cn.zhyocean.service;

import cn.zhyocean.model.ManagerApply;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 15:47
 * Describe: 管理员申请业务逻辑
 */
@Service
public interface ManagerApplyService {

    /**
     * 插入申请
     * @param managerApply managerApply实体类
     * @return 插入结果
     */
    int insertApply(ManagerApply managerApply);

    /**
     * 通过申请状态获得申请信息
     * @param status 状态
     * @return 查询结果
     */
    List<ManagerApply> getTissueByStatus(int status);

    /**
     * 通过组织名更新当前组织申请状态
     * @param status 状态
     * @param applyName 组织名
     * @return 更新结果
     */
    int updateApplyStatus(int status,String applyName);

    /**
     * 通过组织名删除组织
     * @param applyName 组织名
     * @return 删除结果
     */
    int deleteOrgByApplyName(String applyName);
}
