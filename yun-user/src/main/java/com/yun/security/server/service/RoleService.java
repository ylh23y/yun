package com.yun.security.server.service;

import com.yun.security.server.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(String id);
}
