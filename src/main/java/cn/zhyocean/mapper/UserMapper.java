package cn.zhyocean.mapper;

import cn.zhyocean.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangocean
 * @Date: 2018/6/12 14:04
 * Describe:
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where ybId=#{ybId}")
    User getByYbId(int ybId);

}
