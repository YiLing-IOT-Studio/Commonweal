package cn.zhyocean.mapper;

import cn.zhyocean.model.ActiviteApply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
