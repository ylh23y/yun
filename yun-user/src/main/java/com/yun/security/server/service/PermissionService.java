package com.yun.security.server.service;

import com.yun.security.server.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(String id);
}
