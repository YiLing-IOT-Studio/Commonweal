package cn.zhyocean.service;

import cn.zhyocean.model.ActivityApply;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 19:32
 * Describe:
 */
@Service
public interface ActivityApplyService {

    /**
     * 申请活动
     * @param activityApply 活动状态记录
     * @return 申请结果
     */
    int applyForActivity(ActivityApply activityApply);

    /**
     * 通过易班id和活动id查询是否已经提交申请
     * @param ybId 易班id
     * @param activityId 活动id
     * @return 1--已提交申请   0-未提交申请
     */
    int countApplyActivity(int ybId, int activityId);

    /**
     * 通过userId和status查找该用户参加的所有活动id
     * @param userId
     * @param applyStatus
     * @return
     */
    List<Integer> getAllActivityByYbId(int userId);

    /**
     * 通过活动Id获得这些活动的所有信息
     * @param activityId 活动id
     * @return 活动的所有信息
     */
    JSONArray getAllActivityByActivityId(List<Integer> activityId);

    /**
     * 通过活动id和报名成功的状态获得该活动的所有易班id
     * @param activityId 活动id
     * @param status 报名成功的状态
     * @return 用活动的所有易班id
     */
    List<Integer> getYbIdByActivityId(int activityId);

}
