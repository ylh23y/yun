package com.yun.security.server.api;

import java.util.List;

import com.yun.common.api.bean.Result;
import com.yun.common.exception.BizException;
import com.yun.security.server.dto.RoleDto;
import com.yun.security.server.model.Role;

public interface HemiaoRoleApi {
	
	int saveRole(RoleDto roleDto) throws BizException;

	Result<String> getAllRoles(RoleDto roleDto) throws BizException;

	Role getRoleById(String id) throws BizException;

	List<Role> getRolesByUserId(String userId) throws BizException;

	int deleteRoleById(String id) throws BizException;
	
}
