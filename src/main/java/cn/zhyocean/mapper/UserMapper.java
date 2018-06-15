package cn.zhyocean.mapper;

import cn.zhyocean.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    @Select("select * from user where ybId=#{ybId} limit 1")
    User getByYbId(@Param("ybId") int ybId);

    @Insert("insert into user(stuId, stuName, school, major, grade, telephoneNumber, ybId) " +
                                "values(#{user.stuId},#{user.stuName},#{user.school},#{user.major},#{user.grade},#{user.telephoneNumber},#{ybId})")
    void applyForActivity(@Param("user") User user, @Param("ybId") int ybId);

}
