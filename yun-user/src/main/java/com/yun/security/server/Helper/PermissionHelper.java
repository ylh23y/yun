package com.yun.security.server.Helper;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.yun.common.api.bean.Result;
import com.yun.common.enums.ResponseCodeEnum;
import com.yun.common.exception.BizException;
import com.yun.common.utils.Assert;
import com.yun.security.server.api.HemiaoPermissionApi;
import com.yun.security.server.dto.PermissionDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.Permission;
import com.yun.security.server.model.SysUser;
@Component
public class PermissionHelper {
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private HemiaoPermissionApi hemiaoPermissionApi;
	
	public Result<String> getUserByUsername(UserDto userDto) {
		try {
			Assert.isBlank(userDto.getUsername(), "username不能为空！");
			SysUser user = hemiaoPermissionApi.getUserByUsername(userDto);
			return Result.returnResult(user);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<List<Permission>> getPermissionByUserId(PermissionDto permissionDto) {
		try {
			//Assert.isBlank(permissionDto.getUserId(), "userId不能为空！");
			List<Permission> list = hemiaoPermissionApi.getPermissionByUserId(permissionDto);
			return Result.returnResult(list);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getPermissionsList() {
		try {
			List<Permission> list = hemiaoPermissionApi.getPermissionsList();
			return Result.returnResult(list);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getAllpermissionsTree() {
		try {
			JSONArray array = hemiaoPermissionApi.getAllpermissionsTree();
			return Result.returnResult(array);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getParentMenu() {
		try {
			List<Permission> parents = hemiaoPermissionApi.getParentMenu();
			return Result.returnResult(parents);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getPermissionsByRoleId(String roleId) {
		try {
			Assert.isBlank(roleId, "roleId不能为空！");
			List<Permission> list = hemiaoPermissionApi.getPermissionsByRoleId(roleId);
			return Result.returnResult(list);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> savePermission(Permission permission) {
		try {
			Assert.isBlank(permission.getName(), "权限资源名称不能为空！");
			Assert.isBlank(permission.getParentId(), "权限资源parentId不能为空！");
			Assert.isNull(permission.getType(), "权限资源类型不能为空！");
			Assert.isNull(permission.getSort(), "权限资源排序不能为空！");
			int insert = hemiaoPermissionApi.savePermission(permission);
			if (insert > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "保存权限资源失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getPermissionById(String id) {
		try {
			Assert.isBlank(id, "id不能为空！");
			Permission permission = hemiaoPermissionApi.getPermissionById(id);
			return Result.returnResult(permission);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> updatePermission(Permission permission) {
		try {
			Assert.isBlank(permission.getId(), "权限资源ID不能为空！");
			int insert = hemiaoPermissionApi.updatePermission(permission);
			if (insert > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "更新权限资源失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> deletePermission(String id) {
		try {
			Assert.isBlank(id, "权限资源ID不能为空！");
			int insert = hemiaoPermissionApi.deletePermission(id);
			if (insert > 0) {
				return Result.returnResult("success");
			} else {
				return Result.returnExcetionResult(ResponseCodeEnum.ERROR.getCode(), "删除权限资源失败!");
			}
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> checkOwnsPermission(String userId) {
		try {
			Assert.isBlank(userId, "userId不能为空！");
			Set<String> set = hemiaoPermissionApi.checkOwnsPermission(userId);
			return Result.returnResult(set);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

	public Result<String> getCurrentPermissions(String userId) {
		try {
			Assert.isBlank(userId, "userId不能为空！");
			List<Permission> list =  hemiaoPermissionApi.getCurrentPermissions(userId);
			return Result.returnResult(list);
		} catch (BizException e) {
			logger.warn(e.getMsg());
			return Result.returnExcetionResult(e.getBizCode(), e.getMsg());
		}
	}

}
