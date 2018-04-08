package com.yun.security.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yun.security.server.model.SysUser;

@Mapper
public interface UserDao {

	//@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(id, username, password, nickname, headImgUrl, phone, telephone, email, last_password_reset_date ,birthday, sex, status, createTime, updateTime) values(#{id},#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, now(), #{birthday}, #{sex}, #{status}, now(), #{updateTime})")
	int save(SysUser user);

	@Select("select * from sys_user t where t.id = #{id}")
	SysUser getUserById(String id);

	@Select("select * from sys_user t where t.username = #{username}")
	SysUser getUser(String username);

	@Update("update sys_user t set t.password = #{password} ,t.last_password_reset_date = now()  where t.id = #{id}")
	int changePassword(@Param("id") String id, @Param("password") String password);

	Integer count(@Param("params") Map<String, Object> params);

	List<SysUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);

	@Delete("delete from sys_role_user where userId = #{userId}")
	int deleteUserRole(String userId);

	int saveUserRoles(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

	int update(SysUser user);
}
