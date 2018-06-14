package cn.zhyocean.mapper;

import cn.zhyocean.model.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/9 16:33
 * Describe: 活动数据库操作
 */
@Mapper
@Repository
public interface ActivityMapper {

    @Insert("insert into activity(title,category,publisher,img,publishDate,remain,msg,place,deadline,personalLimit,activityDate)" +
                "values(#{title},#{category},#{publisher},#{img},#{publishDate},#{remain},#{msg},#{place},#{deadline},#{personalLimit},#{activityDate})")
    int insertActivity(Activity activity);

    @Select("select * from activity where category=#{tag} limit #{start},#{pageSize}")
    List<Activity> findActivityByTag(@Param("tag") String tag, @Param("pageSize") int pageSize, @Param("start") int start);

    @Select("select * from activity limit #{start},#{pageSize}")
    List<Activity> findAllActivity(@Param("pageSize") int pageSize, @Param("start") int start);

    @Select("select count(*) from activity where category=#{tag}")
    int countActivityByTag(@Param("tag") String tag);

    @Select("select count(*) from activity")
    int countAllActivity();

    @Select("select * from activity where publisher=#{publisher}")
    List<Activity> getAllActivityByPublisher(@Param("publisher") String publisher);

    @Select("select * from activity where id=#{id}")
    Activity getActivityById(@Param("id") int id);

    @Select("select title from activity where publisher=#{publisher}")
    List<String> getActivityNames(@Param("publisher") String publisher);

    @Select("select id from activity where title=#{title} and publisher=#{publisher}")
    int getIdByTitleAndPublisher(@Param("title") String title, @Param("publisher") String publisher);
}
