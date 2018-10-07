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

    @Insert("insert into manager_apply(applyName,superior,evidence,proposer,status) " +
            "values(#{applyName},#{superior},#{evidence},#{proposer},#{status})")
    void insertApply(ManagerApply managerApply);

    @Select("select * from manager_apply where status=#{status}")
    List<ManagerApply> getTissueByStatus(@Param("status") int status);

    @Update("update manager_apply set status=#{status} where applyName=#{applyName}")
    void updateApplyStatus(@Param("status") int status, @Param("applyName") String applyName);

    @Delete("delete from manager_apply where applyName=#{applyName}")
    void deleteOrgByApplyName(@Param("applyName") String applyName);

    @Select("select status from manager_apply where proposer=#{proposer}")
    int getStatusByProposer(@Param("proposer") String proposer);

    @Select("select * from manager_apply where proposer=#{proposer}")
    ManagerApply getByProposer(@Param("proposer") String proposer);

    @Select("select proposer from manager_apply where applyName=#{applyName}")
    String getProposerByApplyName(@Param("applyName") String applyName);

    @Select("select count(*) from manager_apply where applyName=#{applyName}")
    int countByApplyName(@Param("applyName") String applyName);
}
