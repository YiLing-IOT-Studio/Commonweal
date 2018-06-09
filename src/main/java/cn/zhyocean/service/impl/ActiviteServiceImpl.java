package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.ActiviteMapper;
import cn.zhyocean.model.Activite;
import cn.zhyocean.service.ActiviteService;
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
public class ActiviteServiceImpl implements ActiviteService {

    @Autowired
    ActiviteMapper activiteMapper;

    @Override
    public JSONArray findActiviteByTag(String tag, String rows, String pageNo) {
        int startPage = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(rows);
        int start = (startPage-1)*pageSize;

        List<Activite> activiteList;
        Map<String, Integer> count;

        if("全部活动".equals(tag)){
            activiteList = activiteMapper.findAllActivite(pageSize, start);
            count = countAllActivite(pageSize);
        } else {
            activiteList = activiteMapper.findActiviteByTag(tag, pageSize, start);
            count = countActiviteByTag(tag, pageSize);
        }

        JSONArray jsonArray = JSONArray.fromObject(activiteList);
        jsonArray.add(count);
        System.out.println("全部活动信息：" + jsonArray);
        return jsonArray;
    }

    private Map<String, Integer> countActiviteByTag(String tag, int rows) {
        int countActivites = activiteMapper.countActiviteByTag(tag);
        System.out.println("通过标签 " + tag + " 查到了" + countActivites + "条数据");
        Map<String, Integer> map = new HashMap<>();
        map.put("totalSize", countActivites);
        map.put("totalPage", (int) Math.ceil((double)countActivites/rows));
        return map;
    }

    private Map<String, Integer> countAllActivite(int rows) {
        int countActivites = activiteMapper.countAllActivite();
        System.out.println(" 查到了" + countActivites + "条数据");
        Map<String, Integer> map = new HashMap<>();
        map.put("totalSize", countActivites);
        map.put("totalPage", (int) Math.ceil((double)countActivites/rows));
        return map;
    }
}
