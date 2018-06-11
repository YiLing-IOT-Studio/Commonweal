package cn.zhyocean.service;

import cn.zhyocean.model.ActiviteApply;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 19:32
 * Describe:
 */
@Service
public interface ActiviteApplyService {

    /**
     * 申请活动
     * @param activiteApply 活动状态记录
     * @return 申请结果
     */
    int applyForActivite(ActiviteApply activiteApply);

}
