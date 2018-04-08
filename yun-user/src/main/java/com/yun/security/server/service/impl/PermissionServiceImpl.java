package com.yun.security.server.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yun.security.server.dao.PermissionDao;
import com.yun.security.server.model.Permission;
import com.yun.security.server.service.PermissionService;
import com.yun.security.server.service.UserService;

@Service
public class PermissionServiceImpl implements PermissionService {

	private static final Logger log = LoggerFactory.getLogger("PermissionServiceImpl");

	@Resource
	private PermissionDao permissionDao;
	
	@Resource
	private UserService userService;

	@Override
	public void save(Permission permission) {
		permissionDao.save(permission);

		log.debug("新增菜单{}", permission.getName());
	}

	@Override
	public void update(Permission permission) {
		//Set<String> userIds = listUserIds(permission.getId());
		permissionDao.updatePermission(permission);
		//userService.updateLoginUserCache(userIds);
	}

	@Override
	@Transactional
	public void delete(String id) {
		//Set<String> userIds = listUserIds(id);
		permissionDao.deleteRolePermission(id);
		permissionDao.deletePermission(id);
		permissionDao.deletePermissionByParentId(id);

		log.debug("删除菜单id:{}", id);
		//userService.updateLoginUserCache(userIds);
	}

	private Set<String> listUserIds(String permissionId) {
		return permissionDao.listUserIds(permissionId);
	}

}
