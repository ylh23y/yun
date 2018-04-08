package com.yun.security.server.api;

import com.yun.common.api.bean.Result;
import com.yun.common.exception.BizException;
import com.yun.security.server.dto.SysUserDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.SysUser;

public interface HemiaoUserApi {

	SysUser getUserByUsername(SysUserDto userDto) throws BizException;

	SysUser saveUser(UserDto userDto) throws BizException;

	int updateUser(UserDto userDto) throws BizException;

	Result<String> getAllUsers(SysUserDto userDto) throws BizException;

	SysUser getUserById(String id) throws BizException;

	Integer changePassword(SysUserDto userDto) throws BizException;

	int updateHeadImgUrl(SysUserDto userDto) throws BizException;
  
}
