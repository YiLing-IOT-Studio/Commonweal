package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ManagerApplyMapper;
import cn.zhyocean.model.ManagerApply;
import cn.zhyocean.service.ManagerApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 15:48
 * Describe:
 */
@Service
public class ManagerApplyServiceImpl implements ManagerApplyService{

    private final Logger logger = LoggerFactory.getLogger(ManagerApplyServiceImpl.class);

    @Autowired
    ManagerApplyMapper managerApplyMapper;

    @Override
    public int insertApply(ManagerApply managerApply) {
        try {
            managerApplyMapper.insertApply(managerApply);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("插入申请成为管理员信息失败");
            return 0;
        }
    }

    @Override
    public List<ManagerApply> getTissueByStatus(int status) {
        return managerApplyMapper.getTissueByStatus(status);
    }

    @Override
    public int updateApplyStatus(int status, String applyName) {
        try {
            managerApplyMapper.updateApplyStatus(status, applyName);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("更新组织状态失败！");
            return 0;
        }
    }

    @Override
    public int deleteOrgByApplyName(String applyName) {
       try {
           managerApplyMapper.deleteOrgByApplyName(applyName);
           return 1;
       } catch (Exception e){
           e.printStackTrace();
           logger.error("删除组织失败");
           return 0;
       }
    }

    @Override
    public int getStatusByProposer(String proposer) {
        return managerApplyMapper.getStatusByProposer(proposer);
    }
}
