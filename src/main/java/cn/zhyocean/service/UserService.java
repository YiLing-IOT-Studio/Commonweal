package cn.zhyocean.service;

import cn.zhyocean.model.User;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/12 14:07
 * Describe:
 */
@Service
public interface UserService {

    /**
     * 通过易班id获得该用户的学号、专业等信息
     * @param id 易班id
     * @return 该用户的学号、专业等信息
     */
    JSONArray getByYbIds(List<Integer> ids);

    /**
     * 保存活动报名信息
     * @param ybId 易班id
     * @param user 报名信息
     * @param activityName  活动名
     * @return 报名结果
     */
    int applyForActivity(int ybId, User user, String activityName);


}
