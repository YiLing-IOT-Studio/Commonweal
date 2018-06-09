package cn.zhyocean.mapper;

import cn.zhyocean.model.Activite;
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
public interface ActiviteMapper {


    @Select("select * from activite where category=#{tag} limit #{start},#{pageSize}")
    List<Activite> findActiviteByTag(@Param("tag") String tag, @Param("pageSize") int pageSize, @Param("start") int start);

    @Select("select * from activite limit #{start},#{pageSize}")
    List<Activite> findAllActivite(@Param("pageSize") int pageSize, @Param("start") int start);

    @Select("select count(*) from activite where category=#{tag}")
    int countActiviteByTag(@Param("tag") String tag);

    @Select("select count(*) from activite")
    int countAllActivite();
}
