package com.yun.security.server.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yun.security.mapper.SysRolePoMapper;
import com.yun.security.po.SysRolePo;
import com.yun.security.po.SysRolePoExample;
import com.yun.security.po.SysRolePoExample.Criteria;
import com.yun.security.server.api.HemiaoRoleApi;
import com.yun.security.server.dao.RoleDao;
import com.yun.security.server.dto.RoleDto;
import com.yun.security.server.model.Role;

@Service
public class HemiaoRoleApiImpl implements HemiaoRoleApi {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private SysRolePoMapper sysRolePoMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int saveRole(RoleDto roleDto) throws BizException {
		// TODO Auto-generated method stub
		Role role = roleDto;
		List<String> permissionIds = roleDto.getPermissionIds();
		
		if (role.getId() != null) {// 修改
			int updateRole = updateRole(role, permissionIds);
			return updateRole;
		} else {// 新增
			int saveRole = saveRole(role, permissionIds);
			return saveRole;
		}
	}

	private int updateRole(Role role, List<String> permissionIds) throws BizException {
		/*Role r = roleDao.getRole(role.getName());
		if (r != null && r.getId() != role.getId()) {
			throw new BizException(role.getName() + "没有这个角色！");
		}*/
		Role r = roleDao.getRoleById(role.getId());
		if (null == r) {
			throw new BizException(role.getName() + "没有这个角色！");
		}
		int update = roleDao.update(role);
		//Set<String> userIds = listUserIds(role.getId());
		roleDao.deleteRolePermission(role.getId());
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("修改角色{}", role.getName());
		//userService.updateLoginUserCache(userIds);
		return update;
	}

	private int saveRole(Role role, List<String> permissionIds) throws BizException {
		Role r = roleDao.getRole(role.getName());
		if (r != null) {
			throw new BizException(role.getName() + "已存在");
		}
		String roleId = IdWorker.getNextId();
		role.setId(roleId);
		int save = roleDao.save(role);
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleDao.saveRolePermission(roleId, permissionIds);
		}
		log.debug("新增角色{}", role.getName());
		return save;
	}

	@Override
	public Result<String> getAllRoles(RoleDto roleDto) throws BizException {
		SysRolePoExample example = new SysRolePoExample();
		example.setDistinct(true);
		Criteria createCriteria = example.createCriteria();

		if (StringUtils.isNotBlank(roleDto.getName())) {
			createCriteria.andNameLike("%"+roleDto.getName()+"%");
		}
		if (StringUtils.isNotBlank(roleDto.getDescription())) {
			createCriteria.andDescriptionLike("%"+roleDto.getDescription()+"%");
		}
		example.setOrderByClause("createTime asc");
		PageHelper.startPage(roleDto.getPageNo(), roleDto.getPageSize());
		List<SysRolePo> list = sysRolePoMapper.selectByExample(example);
		PageInfo<SysRolePo> gPageInfo = new PageInfo<>(list);
		return Result.returnResult(gPageInfo);
	}

	@Override
	public Role getRoleById(String id) throws BizException {
		// TODO Auto-generated method stub
		Role role = roleDao.getRoleById(id);
		return role;
	}

	@Override
	public List<Role> getRolesByUserId(String userId) throws BizException {
		// TODO Auto-generated method stub
		List<Role> list = roleDao.getRolesByUserId(userId);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int deleteRoleById(String id) throws BizException {
		// TODO Auto-generated method stub
		int delete = roleDao.deleteRoleById(id);
		return delete;
	}
	
}
