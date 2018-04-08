package com.yun.security.server.api.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yun.common.api.bean.Result;
import com.yun.common.exception.BizException;
import com.yun.common.utils.IdWorker;
import com.yun.security.mapper.SysUserPoMapper;
import com.yun.security.po.SysUserPo;
import com.yun.security.po.SysUserPoExample;
import com.yun.security.po.SysUserPoExample.Criteria;
import com.yun.security.server.api.HemiaoUserApi;
import com.yun.security.server.dao.UserDao;
import com.yun.security.server.dto.SysUserDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.SysUser;
import com.yun.security.server.model.SysUser.Status;


@Service
public class HemiaoUserApiImpl implements HemiaoUserApi {

	private  Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserDao userDao;
			
	@Resource
	private SysUserPoMapper sysUserPoMapper;
	
	@Resource
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public SysUser getUserByUsername(SysUserDto userDto) throws BizException {
		// TODO Auto-generated method stub
		SysUser user = userDao.getUser(userDto.getUsername());
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public SysUser saveUser(UserDto userDto) throws BizException {
		SysUser u = userDao.getUser(userDto.getUsername());
		if (u != null) {
			throw new BizException(userDto.getUsername() + "已存在");
		}	
		SysUser saveUser = saveSysUser(userDto);				
		return saveUser;
	}

	public SysUser saveSysUser(UserDto userDto) {
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
	
	/**
	 * 保存用户角色更新
	 * @param userId
	 * @param roleIds
	 */
	private void saveUserRoles(String userId, List<String> roleIds) {
		if (roleIds != null) {
			userDao.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userDao.saveUserRoles(userId, roleIds);
			}
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int updateUser(UserDto userDto) throws BizException {
		userDto.setUpdateTime(new Date());
		if (StringUtils.isNotBlank(userDto.getPassword())) {
			String password = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(password);
			userDto.setLastPasswordResetDate(new Date());
		}
		int update = userDao.update(userDto);		
	
		saveUserRoles(userDto.getId(), userDto.getRoleIds());
		return update;
	}






	@Override
	public Result<String> getAllUsers(SysUserDto userDto) throws BizException {
		// TODO Auto-generated method stub
		SysUserPoExample example = new SysUserPoExample();
		example.setDistinct(true);
		Criteria createCriteria = example.createCriteria();
		if (SysUser.Status.ENABLED.equals(userDto.getStatus())) {
			createCriteria.andStatusEqualTo(0);
		}else if (SysUser.Status.DISABLED.equals(userDto.getStatus())) {
			createCriteria.andStatusEqualTo(1);
		}else if (SysUser.Status.DELETE.equals(userDto.getStatus())) {
			createCriteria.andStatusEqualTo(2);
		}
		if (StringUtils.isNotBlank(userDto.getUsername())) {
			createCriteria.andUsernameLike("%"+userDto.getUsername()+"%");
		}
		if (StringUtils.isNotBlank(userDto.getNickname())) {
			createCriteria.andNicknameLike("%"+userDto.getNickname()+"%");
		}
		example.setOrderByClause("createTime desc");
		PageHelper.startPage(userDto.getPageNo(), userDto.getPageSize());
		List<SysUserPo> list = sysUserPoMapper.selectByExample(example);
		PageInfo<SysUserPo> gPageInfo = new PageInfo<>(list);
		return Result.returnResult(gPageInfo);
	}

	@Override
	public SysUser getUserById(String id) throws BizException {
		SysUser user = userDao.getUserById(id);		
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public Integer changePassword(SysUserDto userDto) throws BizException {
		SysUser u = userDao.getUser(userDto.getUsername());
		if (u == null) {
			throw new BizException("用户不存在");
		}
		if (!passwordEncoder.matches(userDto.getOldPassword(), u.getPassword())) {
			throw new BizException("旧密码错误");
		}
		int changePassword = userDao.changePassword(u.getId(), passwordEncoder.encode(userDto.getNewPassword()));
		return changePassword;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int updateHeadImgUrl(SysUserDto sysUserDto) throws BizException {
		// TODO Auto-generated method stub
		SysUser user = userDao.getUserById(sysUserDto.getUserId());
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userDto.setHeadImgUrl(sysUserDto.getHeadImgUrl());	
		userDto.setUpdateTime(new Date());
		int updateUser = userDao.update(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());		
		log.debug("{}修改了头像", user.getUsername());		
		return updateUser;
	}
	
}
