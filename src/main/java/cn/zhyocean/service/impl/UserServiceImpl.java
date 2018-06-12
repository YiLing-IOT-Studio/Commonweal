package cn.zhyocean.service.impl;

import cn.zhyocean.mapper.UserMapper;
import cn.zhyocean.model.User;
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
}
