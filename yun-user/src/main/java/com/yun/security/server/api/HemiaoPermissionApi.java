package com.yun.security.server.api;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.yun.common.exception.BizException;
import com.yun.security.server.dto.PermissionDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.Permission;
import com.yun.security.server.model.SysUser;

public interface HemiaoPermissionApi {

	SysUser getUserByUsername(UserDto userDto) throws BizException;

	List<Permission> getPermissionByUserId(PermissionDto permissionDto) throws BizException;

	List<Permission> getPermissionsList() throws BizException;

	JSONArray getAllpermissionsTree() throws BizException;

	List<Permission> getParentMenu() throws BizException;

	List<Permission> getPermissionsByRoleId(String roleId) throws BizException;

	int savePermission(Permission permission) throws BizException;

	Permission getPermissionById(String id) throws BizException;

	int updatePermission(Permission permission) throws BizException;

	int deletePermission(String id) throws BizException;

	Set<String> checkOwnsPermission(String userId) throws BizException;

	List<Permission> getCurrentPermissions(String userId) throws BizException;
  
}
