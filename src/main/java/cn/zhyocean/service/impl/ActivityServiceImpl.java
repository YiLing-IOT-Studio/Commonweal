package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ActivityMapper;
import cn.zhyocean.model.Activity;
import cn.zhyocean.service.ActivityService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 16:33
 * Describe:
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public JSONArray findActivityByTag(String tag, String rows, String pageNo) {
        int startPage = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(rows);
        int start = (startPage-1)*pageSize;

        List<Activity> activityList;
        Map<String, Integer> count;

        if("全部活动".equals(tag)){
            activityList = activityMapper.findAllActivity(pageSize, start);
            count = countAllActivity(pageSize);
        } else {
            activityList = activityMapper.findActivityByTag(tag, pageSize, start);
            count = countActivityByTag(tag, pageSize);
        }

        JSONArray jsonArray = JSONArray.fromObject(activityList);
        jsonArray.add(count);
        System.out.println("全部活动信息：" + jsonArray);
        return jsonArray;
    }

    @Override
    public void insertActivity(Activity activity) {
        int result = activityMapper.insertActivity(activity);
        System.out.println("插入结构result：" +result);
        System.out.println("公益活动信息插入成功！");
    }

    @Override
    public JSONArray getAllActivityByPublisher(String publisher) {
        List<Activity> allActivities = activityMapper.getAllActivityByPublisher(publisher);
        System.out.println("该发布者发布的所有活动：" + JSONArray.fromObject(allActivities));
        return JSONArray.fromObject(allActivities);
    }

    @Override
    public JSONArray getActivityNamesByPublisher(String publsiher) {
        List<String> activityNames = activityMapper.getActivityNames(publsiher);

        JSONArray jsonArray = JSONArray.fromObject(activityNames);
        System.out.println("该发布者发布的活动名有：" + jsonArray);
        return jsonArray;
    }

    @Override
    public int getIdByTitleAndPublisher(String title, String publisher) {
        return activityMapper.getIdByTitleAndPublisher(title, publisher);
    }

    @Override
    public int getIdByTitle(String title) {
        return activityMapper.getIdByTitle(title);
    }

    @Override
    public int getRemainByTitle(String title) {
        return activityMapper.getRemainByTitle(title);
    }

    @Override
    public void updateRemainByTitle(String title) {
        activityMapper.updateRemainByTitle(title);
    }

    private Map<String, Integer> countActivityByTag(String tag, int rows) {
        int countActivitys = activityMapper.countActivityByTag(tag);
        System.out.println("通过标签 " + tag + " 查到了" + countActivitys + "条数据");
        Map<String, Integer> map = new HashMap<>();
        map.put("totalSize", countActivitys);
        map.put("totalPage", (int) Math.ceil((double)countActivitys/rows));
        return map;
    }

    private Map<String, Integer> countAllActivity(int rows) {
        int countActivitys = activityMapper.countAllActivity();
        System.out.println(" 查到了" + countActivitys + "条数据");
        Map<String, Integer> map = new HashMap<>();
        map.put("totalSize", countActivitys);
        map.put("totalPage", (int) Math.ceil((double)countActivitys/rows));
        return map;
    }
}
