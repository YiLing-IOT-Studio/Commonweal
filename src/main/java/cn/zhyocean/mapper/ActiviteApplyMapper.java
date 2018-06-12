package cn.zhyocean.mapper;

import cn.zhyocean.model.ActiviteApply;
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
public interface ActiviteApplyMapper {

    @Insert("insert into activiteapply(activiteId, userId, applyStatus) values(#{activiteId},#{userId},#{applyStatus})")
    void applyForActivite(ActiviteApply activiteApply);

    @Select("select activiteId from activiteapply where userId=#{userId} and applyStatus=#{applyStatus}")
    List<Integer> getAllActivitesByUserIdAndApplyStatus(@Param("userId") int userId, @Param("applyStatus") int applyStatus );

    @Select("select userId from activiteapply where activiteId=#{activiteId} and applyStatus=#{applyStatus}")
    List<Integer> getUserIdByActiviteIdAndStatus(@Param("activiteId") int activiteId, @Param("applyStatus") int applyStatus);
}
