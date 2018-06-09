package cn.zhyocean.service;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 16:31
 * Describe: 活动相关业务
 */
@Service
public interface ActiviteService {

    /**
     * 通过标签查找活动
     * @param tag 标签
     * @return 所有相关活动
     */
    JSONArray findActiviteByTag(String tag, String rows, String pageNo);


}
