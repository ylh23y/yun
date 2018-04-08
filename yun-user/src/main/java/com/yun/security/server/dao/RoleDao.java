package com.yun.security.server.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yun.security.server.model.Role;

@Mapper
public interface RoleDao {

	//@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_role(id, name, description, createTime) values(#{id}, #{name}, #{description}, now())")
	int save(Role role);

	int count(@Param("params") Map<String, Object> params);

	List<Role> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);

	@Select("select * from sys_role t where t.id = #{id}")
	Role getRoleById(String id);

	@Select("select * from sys_role t where t.name = #{name}")
	Role getRole(String name);

	@Update("update sys_role t set t.name = #{name}, t.description = #{description}, updateTime = now() where t.id = #{id}")
	int update(Role role);

	@Select("select * from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.userId = #{userId}")
	List<Role> getRolesByUserId(String userId);

	@Delete("delete from sys_role_permission where roleId = #{roleId}")
	int deleteRolePermission(String roleId);

	int saveRolePermission(@Param("roleId") String roleId, @Param("permissionIds") List<String> permissionIds);

	@Delete("delete from sys_role where id = #{id}")
	int deleteRoleById(String id);

	@Delete("delete from sys_role_user where roleId = #{roleId}")
	int deleteRoleUser(String roleId);
	
	@Select("select ru.userId from sys_role r inner join sys_role_user ru on r.id = ru.roleId where ru.roleId = #{roleId}")
	Set<String> listUserIds(String roleId);
}
