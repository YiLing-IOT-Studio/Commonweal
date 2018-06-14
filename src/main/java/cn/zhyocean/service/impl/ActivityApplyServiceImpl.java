package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ActivityApplyMapper;
import cn.zhyocean.mapper.ActivityMapper;
import cn.zhyocean.model.Activity;
import cn.zhyocean.model.ActivityApply;
import cn.zhyocean.service.ActivityApplyService;
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
public class ActivityApplyServiceImpl implements ActivityApplyService {

    private Logger logger = LoggerFactory.getLogger(ActivityApplyServiceImpl.class);

    @Autowired
    ActivityApplyMapper activityApplyMapper;
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public int applyForActivity(ActivityApply activityApply) {
        try {
            activityApplyMapper.applyForActivity(activityApply);
            logger.info("申请活动成功");
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            logger.error("申请活动失败");
        }
        return 0;
    }

    @Override
    public List<Integer> getAllActivitysByUserIdAndApplyStatus(int userId, int applyStatus) {
        return activityApplyMapper.getAllActivitysByUserIdAndApplyStatus(userId, applyStatus);
    }

    @Override
    public JSONArray getAllActivitysByActivityId(List<Integer> activityId) {
        List<Activity> activities = new ArrayList<>();
        Activity activity;
        for(Integer id : activityId){
            activity = activityMapper.getActivityById(id);
            activities.add(activity);
        }
        JSONArray jsonArray = JSONArray.fromObject(activities);
        System.out.println("该用户参加的所有活动有：" + jsonArray);
        return jsonArray;
    }

    @Override
    public List<Integer> getUserIdByActivityIdAndStatus(int activityId, int status) {
        return activityApplyMapper.getUserIdByActivityIdAndStatus(activityId, status);
    }
}
