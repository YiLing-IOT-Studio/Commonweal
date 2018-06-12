package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ActiviteApplyMapper;
import cn.zhyocean.mapper.ActiviteMapper;
import cn.zhyocean.model.Activite;
import cn.zhyocean.model.ActiviteApply;
import cn.zhyocean.service.ActiviteApplyService;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 19:33
 * Describe:
 */
@Service
public class ActiviteApplyServiceImpl implements ActiviteApplyService {

    private Logger logger = LoggerFactory.getLogger(ActiviteApplyServiceImpl.class);

    @Autowired
    ActiviteApplyMapper activiteApplyMapper;
    @Autowired
    ActiviteMapper activiteMapper;

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

    @Override
    public List<Integer> getAllActivitesByUserIdAndApplyStatus(int userId, int applyStatus) {
        return activiteApplyMapper.getAllActivitesByUserIdAndApplyStatus(userId, applyStatus);
    }

    @Override
    public JSONArray getAllActivitesByActiviteId(List<Integer> activiteId) {
        List<Activite> activites = new ArrayList<>();
        Activite activite;
        for(Integer id : activiteId){
            activite = activiteMapper.getActiviteById(id);
            activites.add(activite);
        }
        JSONArray jsonArray = JSONArray.fromObject(activites);
        System.out.println("该用户参加的所有活动有：" + jsonArray);
        return jsonArray;
    }

    @Override
    public List<Integer> getUserIdByActiviteIdAndStatus(int activiteId, int status) {
        return activiteApplyMapper.getUserIdByActiviteIdAndStatus(activiteId, status);
    }
}
