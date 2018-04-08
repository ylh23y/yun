package com.yun.security.server.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yun.security.server.model.Permission;

@Mapper
public interface PermissionDao {

	@Select("select * from sys_permission t order by t.sort")
	List<Permission> listAll();

	@Select("select * from sys_permission t where t.type = 1  order by t.sort")
	List<Permission> listParents();

	@Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
	List<Permission> listByUserId(String userId);

	@Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
	List<Permission> listByRoleId(String roleId);

	@Select("select * from sys_permission t where t.id = #{id}")
	Permission getPermissionById(String id);

	@Insert("insert into sys_permission(id,parentId, name, css, href, type, permission, sort, createTime) values(#{id},#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort}, now())")
	int save(Permission permission);

	@Update("update sys_permission t set parentId = #{parentId}, name = #{name}, css = #{css}, href = #{href}, type = #{type}, permission = #{permission}, sort = #{sort} , updateTime = now() where t.id = #{id}")
	int updatePermission(Permission permission);

	@Delete("delete from sys_permission where id = #{id}")
	int deletePermission(String id);

	@Delete("delete from sys_permission where parentId = #{id}")
	int deletePermissionByParentId(String id);

	@Delete("delete from sys_role_permission where permissionId = #{permissionId}")
	int deleteRolePermission(String permissionId);

	@Select("select ru.userId from sys_role_permission rp inner join sys_role_user ru on ru.roleId = rp.roleId where rp.permissionId = #{permissionId}")
	Set<String> listUserIds(String permissionId);
}
