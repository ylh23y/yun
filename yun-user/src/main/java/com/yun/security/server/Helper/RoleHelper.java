package com.yun.security.server.Helper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yun.common.api.bean.Result;
import com.yun.common.enums.ResponseCodeEnum;
import com.yun.common.exception.BizException;
import com.yun.common.utils.Assert;
import com.yun.security.server.api.HemiaoRoleApi;
import com.yun.security.server.dto.RoleDto;
import com.yun.security.server.model.Role;
@Component
public class RoleHelper {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private HemiaoRoleApi hemiaoRoleApi;
	
	
	public Result<String> saveRole(RoleDto roleDto) {
		try {
			//Assert.isBlank(roleDto.getUsername(), "用户名不能为空！");
			int insert = hemiaoRoleApi.saveRole(roleDto);
			if (insert > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "更新用户信息失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getAllRoles(RoleDto roleDto) {
		try {
			Assert.isNull(roleDto.getPageNo(), "分页查询的页号不能为空！");
			Assert.isNull(roleDto.getPageSize(), "分页查询的每页显示条数不能为空！");
			Result<String> list = hemiaoRoleApi.getAllRoles(roleDto);
			return list;
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getRoleById(String id) {
		try {
			Assert.isBlank(id, "角色id不能为空！");
			Role role = hemiaoRoleApi.getRoleById(id);
			return Result.returnResult(role);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getRolesByUserId(String userId) {
		try {
			Assert.isBlank(userId, "用户id不能为空！");
			List<Role> list = hemiaoRoleApi.getRolesByUserId(userId);
			return Result.returnResult(list);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> deleteRoleById(String id) {
		try {
			Assert.isBlank(id, "角色id不能为空！");
			int delete = hemiaoRoleApi.deleteRoleById(id);
			if (delete > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "删除角色失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}
	
}
