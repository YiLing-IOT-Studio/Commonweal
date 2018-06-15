package cn.zhyocean.mapper;

import cn.zhyocean.model.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zhangocean
 * @Date: 2018/6/13 13:06
 * Describe:
 */
@Mapper
@Repository
public interface RoleMapper {

    @Insert("insert into role(username,role) values(#{username},#{role})")
    void insertRole(Role role);

    @Delete("delete from role where username=#{username}")
    void deleteRoleByUsername(@Param("username") String username);

    @Select("select role from role where username=#{username}")
    List<String> getRoleByUsername(@Param("username") String username);
}
