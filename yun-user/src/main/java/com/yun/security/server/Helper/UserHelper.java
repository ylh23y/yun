package com.yun.security.server.Helper;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yun.common.api.bean.Result;
import com.yun.common.enums.ResponseCodeEnum;
import com.yun.common.exception.BizException;
import com.yun.common.utils.Assert;
import com.yun.security.server.api.HemiaoUserApi;
import com.yun.security.server.dto.SysUserDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.SysUser;
@Component
public class UserHelper {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private HemiaoUserApi hemiaoUserApi;
	
	public Result<SysUser> getUserByUsername(SysUserDto userDto) {
		try {
			Assert.isBlank(userDto.getUsername(), "username不能为空！");
			SysUser user = hemiaoUserApi.getUserByUsername(userDto);
			return Result.returnResult(user);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> saveUser(UserDto userDto) {
		try {
			Assert.isBlank(userDto.getUsername(), "用户名不能为空！");
			SysUser sysUser = hemiaoUserApi.saveUser(userDto);
			return Result.returnResult(sysUser);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> updateUser(UserDto userDto) {
		try {
			Assert.isBlank(userDto.getId(), "用户名不能为空！");
			int updateUser = hemiaoUserApi.updateUser(userDto);
			if (updateUser > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "更新用户信息失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}		
	}
	
	
	public Result<String> updateHeadImgUrl(SysUserDto userDto) {
		try {
			Assert.isBlank(userDto.getHeadImgUrl(), "图片url不能为空！");
			Assert.isBlank(userDto.getUserId(), "用户id不能为空！");
			int update = hemiaoUserApi.updateHeadImgUrl(userDto);
			if (update > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "更新头像失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getAllUsers(SysUserDto userDto) {
		try {
			Assert.isNull(userDto.getPageNo(), "分页查询的页号不能为空！");
			Assert.isNull(userDto.getPageSize(), "分页查询的每页显示条数不能为空！");
			Result<String> list = hemiaoUserApi.getAllUsers(userDto);
			return list;
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getUserById(String id) {
		try {
			Assert.isBlank(id, "用户id不能为空！");
			SysUser sysUser = hemiaoUserApi.getUserById(id);
			return Result.returnResult(sysUser);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> changePassword(SysUserDto userDto) {
		try {
			Assert.isBlank(userDto.getUsername(), "用户名不能为空！");
			Assert.isBlank(userDto.getOldPassword(), "旧密码不能为空！");
			Assert.isBlank(userDto.getNewPassword(), "新密码不能为空！");
			Integer update = hemiaoUserApi.changePassword(userDto);
			if (update > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "更新用户密码失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}


}
