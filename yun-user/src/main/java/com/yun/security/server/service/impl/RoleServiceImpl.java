package com.yun.security.server.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yun.common.utils.IdWorker;
import com.yun.security.server.dao.RoleDao;
import com.yun.security.server.dto.RoleDto;
import com.yun.security.server.model.Role;
import com.yun.security.server.service.RoleService;
import com.yun.security.server.service.UserService;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger log = LoggerFactory.getLogger("RoleServiceImpl");

	@Resource
	private RoleDao roleDao;
	
	@Resource
	private UserService userService;

	@Override
	@Transactional
	public void saveRole(RoleDto roleDto) {
		Role role = roleDto;
		List<String> permissionIds = roleDto.getPermissionIds();
		permissionIds.remove(0);
		if (role.getId() != null) {// 修改
			updateRole(role, permissionIds);
		} else {// 新增
			saveRole(role, permissionIds);
		}
	}

	private void saveRole(Role role, List<String> permissionIds) {
		Role r = roleDao.getRole(role.getName());
		if (r != null) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}
		String roleId = IdWorker.getNextId();
		role.setId(roleId);
		roleDao.save(role);
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("新增角色{}", role.getName());
	}

	private void updateRole(Role role, List<String> permissionIds) {
		Role r = roleDao.getRole(role.getName());
		if (r != null && r.getId() != role.getId()) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}
		roleDao.update(role);
		//Set<String> userIds = listUserIds(role.getId());
		roleDao.deleteRolePermission(role.getId());
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("修改角色{}", role.getName());
		//userService.updateLoginUserCache(userIds);
	}

	@Override
	@Transactional
	public void deleteRole(String id) {
		//Set<String> userIds = listUserIds(id);
		roleDao.deleteRolePermission(id);
		roleDao.deleteRoleUser(id);
		roleDao.deleteRoleById(id);
		log.debug("删除角色id:{}", id);
		//userService.updateLoginUserCache(userIds);
	}

	private Set<String> listUserIds(String roleId) {
		return roleDao.listUserIds(roleId);
	}

}
