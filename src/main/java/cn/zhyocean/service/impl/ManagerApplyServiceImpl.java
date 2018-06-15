package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ManagerApplyMapper;
import cn.zhyocean.model.ManagerApply;
import cn.zhyocean.model.Role;
import cn.zhyocean.service.ManagerApplyService;
import cn.zhyocean.service.RoleService;
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
    @Autowired
    RoleService roleService;

    @Override
    public int insertApply(ManagerApply managerApply) {
        try {
            ManagerApply managerApply1 = managerApplyMapper.getByProposer(managerApply.getProposer());
            int applyNameIsExitResult = managerApplyMapper.countByApplyName(managerApply.getApplyName());
            if(managerApply1 == null){
                if(applyNameIsExitResult < 1){
                    managerApplyMapper.insertApply(managerApply);
                    return 1;
                }
                return 3;
            }
            return 2;
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
            if(status == 1){
                String username = managerApplyMapper.getProposerByApplyName(applyName);
                Role role = new Role(username, cn.zhyocean.constant.Role.ROLE_MANAGER);
                //为该用户赋予发布者权限
                roleService.insertRole(role);
            }
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
           String proposer = managerApplyMapper.getProposerByApplyName(applyName);
           managerApplyMapper.deleteOrgByApplyName(applyName);
           roleService.deleteRoleByUsername(proposer);
           return 1;
       } catch (Exception e){
           e.printStackTrace();
           logger.error("删除组织失败");
           return 0;
       }
    }

    @Override
    public int getStatusByProposer(String proposer) {
        int status = 0;
        try {
            status = managerApplyMapper.getStatusByProposer(proposer);
        } catch (Exception e){
            logger.info("该用户未申请称为发布者");
            return 2;
        }
        return status;
    }
}
