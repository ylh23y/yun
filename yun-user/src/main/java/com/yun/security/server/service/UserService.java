package com.yun.security.server.service;

import com.yun.common.api.bean.Result;
import com.yun.security.server.dto.SysUserDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.SysUser;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	int updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

	//void updateLoginUserCache(Set<String> userIds);

	Result<String> getAllUsers(SysUserDto userDto);
}
