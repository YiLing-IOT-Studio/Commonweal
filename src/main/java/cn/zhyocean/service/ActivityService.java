package cn.zhyocean.service;

import cn.zhyocean.model.Activity;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 16:31
 * Describe: 活动相关业务
 */
@Service
public interface ActivityService {

    /**
     * 通过标签查找活动
     * @param tag 标签
     * @return 所有相关活动
     */
    JSONArray findActivityByTag(String tag, String rows, String pageNo);

    /**
     * 发布公益活动
     * @param activity 活动实体类
     */
    void insertActivity(Activity activity);

    /**
     * 通过发布者获得所有活动
     * @param publisher 发布者
     * @return 该发布者发布的所有活动
     */
    JSONArray getAllActivityByPublisher(String publisher);

    /**
     * 通过发布者获得其发布的所有活动名
     * @param publsiher 发布者
     * @return 活动名的数组
     */
    JSONArray getActivityNamesByPublisher(String publsiher);

    /**
     * 通过活动名和发布者获得活动id
     * @param title 活动名
     * @param publisher 发布者
     * @return 活动id
     */
    int getIdByTitleAndPublisher(String title, String publisher);

    /**
     * 通过活动名获得活动id
     * @param title 活动名
     * @return 活动id
     */
    int getIdByTitle(String title);

    /**
     * 通过活动名获得该活动剩余名额
     * @param title 活动名
     * @return 剩余名额
     */
    int getRemainByTitle(String title);

    /**
     * 通过活动名更新剩余名额
     * @param title 活动名
     */
    void updateRemainByTitle(String title);
}
