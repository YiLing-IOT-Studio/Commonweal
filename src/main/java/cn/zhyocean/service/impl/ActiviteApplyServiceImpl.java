package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ActiviteApplyMapper;
import cn.zhyocean.model.ActiviteApply;
import cn.zhyocean.service.ActiviteApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 19:33
 * Describe:
 */
@Service
public class ActiviteApplyServiceImpl implements ActiviteApplyService {

    private Logger logger = LoggerFactory.getLogger(ActiviteApplyServiceImpl.class);

    ActiviteApplyMapper activiteApplyMapper;

    @Override
    public int applyForActivite(ActiviteApply activiteApply) {
        try {
            activiteApplyMapper.applyForActivite(activiteApply);
            logger.info("申请活动成功");
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("申请活动失败");
        }
        return 0;
    }
}
