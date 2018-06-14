package cn.zhyocean.mapper;

import cn.zhyocean.model.ActivityApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 19:29
 * Describe:
 */
@Mapper
@Repository
public interface ActivityApplyMapper {

    @Insert("insert into activityapply(activityId, userId, applyStatus) values(#{activityId},#{userId},#{applyStatus})")
    void applyForActivity(ActivityApply activityApply);

    @Select("select activityId from activityapply where userId=#{userId} and applyStatus=#{applyStatus}")
    List<Integer> getAllActivitysByUserIdAndApplyStatus(@Param("userId") int userId, @Param("applyStatus") int applyStatus );

    @Select("select userId from activityapply where activityId=#{activityId} and applyStatus=#{applyStatus}")
    List<Integer> getUserIdByActivityIdAndStatus(@Param("activityId") int activityId, @Param("applyStatus") int applyStatus);
}
