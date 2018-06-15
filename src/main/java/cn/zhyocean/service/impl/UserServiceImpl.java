package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.UserMapper;
import cn.zhyocean.model.ActivityApply;
import cn.zhyocean.model.User;
import cn.zhyocean.service.ActivityApplyService;
import cn.zhyocean.service.ActivityService;
import cn.zhyocean.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/12 14:07
 * Describe:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ActivityApplyService activityApplyService;
    @Autowired
    ActivityService activityService;

    @Override
    public JSONArray getByYbIds(List<Integer> ids) {

        List<User> list = new ArrayList<>();
        for(int id : ids){
            User user = userMapper.getByYbId(id);
            if(user != null){
                list.add(user);
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println("参加该活动的人员有：" + jsonArray);
        return jsonArray;
    }

    @Override
    public int applyForActivity(int ybId, User user, String activityName) {
        int titleId = activityService.getIdByTitle(activityName);
        int countApplyNum = activityApplyService.countApplyActivity(ybId, titleId);
        if(countApplyNum >= 1){
            System.out.println("您已经报名了该活动");
            return 1;
        }
        try {
            int remain = activityService.getRemainByTitle(activityName);
            if(remain <= 0){
                return 2;
            }
            activityService.updateRemainByTitle(activityName);
            userMapper.applyForActivity(user,ybId);
            ActivityApply activityApply = new ActivityApply(titleId, ybId);
            System.out.println(activityApply);
            int applyResult = activityApplyService.applyForActivity(activityApply);
            if(applyResult == 1){
                return 3;
            }
            return 4;
        } catch (Exception e){
            return 4;
        }
    }
}
