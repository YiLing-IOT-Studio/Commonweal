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

    @Insert("insert into activity_apply(activityId, ybId) values(#{activityId},#{ybId})")
    void applyForActivity(ActivityApply activityApply);

    @Select("select activityId from activity_apply where ybId=#{ybId}")
    List<Integer> getAllActivityByYbId(@Param("ybId") int ybId);

    @Select("select ybId from activity_apply where activityId=#{activityId}")
    List<Integer> getYbIdByActivityId(@Param("activityId") int activityId);

    @Select("select count(*) from activity_apply where ybId=#{ybId} and activityId=#{activityId}")
    int countApplyActivity(@Param("ybId") int ybId, @Param("activityId") int activityId);
}
