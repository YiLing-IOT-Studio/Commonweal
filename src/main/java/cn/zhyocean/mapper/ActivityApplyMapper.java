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

    @Insert("insert into activityapply(activityId, ybId) values(#{activityId},#{ybId})")
    void applyForActivity(ActivityApply activityApply);

    @Select("select activityId from activityapply where ybId=#{ybId}")
    List<Integer> getAllActivityByYbId(@Param("ybId") int ybId);

    @Select("select ybId from activityapply where activityId=#{activityId}")
    List<Integer> getYbIdByActivityId(@Param("activityId") int activityId);

    @Select("select count(*) from activityapply where ybId=#{ybId} and activityId=#{activityId}")
    int countApplyActivity(@Param("ybId") int ybId, @Param("activityId") int activityId);
}
