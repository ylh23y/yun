package com.yun.security.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yun.common.api.bean.Result;
import com.yun.common.utils.IdWorker;
import com.yun.security.server.dao.PermissionDao;
import com.yun.security.server.dao.UserDao;
import com.yun.security.server.dto.SysUserDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.SysUser;
import com.yun.security.server.model.SysUser.Status;
import com.yun.security.server.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private  Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private UserDao userDao;
	
	@Resource
	private BCryptPasswordEncoder passwordEncoder;
	
	@Resource
	private PermissionDao permissionDao;
	
	/*@Resource
	private TokenService tokenService;*/

	@Override
	@Transactional
	public SysUser saveUser(UserDto userDto) {
		SysUser user = userDto;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(Status.ENABLED);
		String userId = IdWorker.getNextId();
		user.setCreateTime(new Date());
		user.setId(userId);
		user.setLastPasswordResetDate(new Date());
		userDao.save(user);
		saveUserRoles(userId, userDto.getRoleIds());
		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	@Override
	public Result<String> getAllUsers(SysUserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void saveUserRoles(String userId, List<String> roleIds) {
		if (roleIds != null) {
			userDao.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userDao.saveUserRoles(userId, roleIds);
			}
		}
	}

	@Override
	public SysUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public void changePassword(String username, String oldPassword, String newPassword) {
		SysUser u = userDao.getUser(username);
		if (u == null) {
			throw new IllegalArgumentException("用户不存在");
		}

		if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
			throw new IllegalArgumentException("旧密码错误");
		}

		userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));

		log.debug("修改{}的密码", username);
	}

	@Override
	@Transactional
	public int updateUser(UserDto userDto) {
		userDto.setUpdateTime(new Date());
		int update = userDao.update(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());
		//updateLoginUserCache(Sets.newHashSet(userDto.getId()));
		return update;
	}

	/**
	 * 修改登陆用户的缓存
	 */
	/*@Override
	public void updateLoginUserCache(Set<String> userIds) {
		if (CollectionUtils.isEmpty(userIds)) {
			return;
		}
		userIds.parallelStream().forEach(userId -> {
			String token = tokenService.getTokenByUserId(userId);
			if (!StringUtils.isEmpty(token)) {
				SysUser sysUser = userDao.getUserById(userId);
				LoginUser loginUser = new LoginUser();
				loginUser.setToken(token);
				BeanUtils.copyProperties(sysUser, loginUser);
				List<Permission> permissions = permissionDao.listByUserId(sysUser.getId());
				loginUser.setPermissions(permissions);
				tokenService.updateLoginUser(loginUser);
			}
		});
	}*/


}
