package cn.zhyocean.mapper;

import cn.zhyocean.model.ManagerApply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/11 15:44
 * Describe:
 */
@Mapper
@Repository
public interface ManagerApplyMapper {

    @Insert("insert into managerapply(applyName,superior,evidence,proposer,status) " +
            "values(#{applyName},#{superior},#{evidence},#{proposer},#{status})")
    void insertApply(ManagerApply managerApply);

    @Select("select * from managerapply where status=#{status}")
    List<ManagerApply> getTissueByStatus(@Param("status") int status);

    @Update("update managerapply set status=#{status} where applyName=#{applyName}")
    void updateApplyStatus(@Param("status") int status, @Param("applyName") String applyName);

    @Delete("delete from managerapply where applyName=#{applyName}")
    void deleteOrgByApplyName(@Param("applyName") String applyName);

    @Select("select status from managerapply where proposer=#{proposer}")
    int getStatusByProposer(@Param("proposer") String proposer);
}
