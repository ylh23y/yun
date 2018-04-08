package com.yun.security.server.api.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.yun.common.exception.BizException;
import com.yun.common.utils.IdWorker;
import com.yun.security.mapper.SysPermissionPoMapper;
import com.yun.security.po.SysPermissionPo;
import com.yun.security.server.api.HemiaoPermissionApi;
import com.yun.security.server.dao.PermissionDao;
import com.yun.security.server.dao.UserDao;
import com.yun.security.server.dto.PermissionDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.Permission;
import com.yun.security.server.model.SysUser;


@Service
public class HemiaoPermissionApiImpl implements HemiaoPermissionApi {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private PermissionDao permissionDao;
	
	@Resource
	private SysPermissionPoMapper sysPermissionPoMapper;
	
	@Override
	public SysUser getUserByUsername(UserDto userDto) throws BizException {
		// TODO Auto-generated method stub
		SysUser user = userDao.getUser(userDto.getUsername());
		return user;
	}

	@Override
	public List<Permission> getPermissionByUserId(PermissionDto permissionDto) throws BizException {
		// TODO Auto-generated method stub
		List<Permission> list = permissionDao.listByUserId(permissionDto.getUserId());
		return list;
	}

	@Override
	public List<Permission> getPermissionsList() throws BizException {
		// TODO Auto-generated method stub
		List<Permission> permissionsAll = permissionDao.listAll();
		List<Permission> list = Lists.newArrayList();
		setPermissionsList("0", permissionsAll, list);
		return list;
	}
	
	/**
	 * 菜单列表
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param list
	 */
	private void setPermissionsList(String pId, List<Permission> permissionsAll, List<Permission> list) {
		for (Permission per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				list.add(per);
				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					setPermissionsList(per.getId(), permissionsAll, list);
				}
			}
		}
	}

	@Override
	public JSONArray getAllpermissionsTree() throws BizException {
		List<Permission> permissionsAll = permissionDao.listAll();
		JSONArray array = new JSONArray();
		setPermissionsTree("0", permissionsAll, array);
		return array;
	}

	
	/**
	 * 菜单树
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param array
	 */
	private void setPermissionsTree(String pId, List<Permission> permissionsAll, JSONArray array) {
		for (Permission per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				String string = JSONObject.toJSONString(per);
				JSONObject parent = (JSONObject) JSONObject.parse(string);
				array.add(parent);
				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					JSONArray child = new JSONArray();
					parent.put("child", child);
					setPermissionsTree(per.getId(), permissionsAll, child);
				}
			}
		}
	}

	@Override
	public List<Permission> getParentMenu() throws BizException {
		List<Permission> parents = permissionDao.listParents();
		return parents;
	}

	@Override
	public List<Permission> getPermissionsByRoleId(String roleId) throws BizException {
		return permissionDao.listByRoleId(roleId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int savePermission(Permission permission) throws BizException {
		String nextId = IdWorker.getNextId();
		permission.setId(nextId);
		permission.setCreateTime(new Date());
		int save = permissionDao.save(permission);
		return save;
	}

	@Override
	public Permission getPermissionById(String id) throws BizException {
		return permissionDao.getPermissionById(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int updatePermission(Permission permission) throws BizException {
		//int update = permissionDao.updatePermission(permission);
		int update = 0;
		SysPermissionPo permissionPo = sysPermissionPoMapper.selectByPrimaryKey(permission.getId());
		if (null!=permissionPo) {		
			if (StringUtils.isNotBlank(permission.getParentId())) {
				permissionPo.setParentid(permission.getParentId());
			}			
			if (StringUtils.isNotBlank(permission.getName())) {
				permissionPo.setName(permission.getName());
			}		
			if (StringUtils.isNotBlank(permission.getCss())) {
				permissionPo.setCss(permission.getCss());
			}
			if (StringUtils.isNotBlank(permission.getHref())) {
				permissionPo.setHref(permission.getHref());
			}
			if (StringUtils.isNotBlank(permission.getPermission())) {
				permissionPo.setPermission(permission.getPermission());
			}
			if (null!=permission.getSort()) {
				permissionPo.setSort(permission.getSort());
			}
			if (null!=permission.getType()) {
				permissionPo.setType(permission.getType());
			}
			permissionPo.setUpdatetime(new Date());
			update = sysPermissionPoMapper.updateByPrimaryKeySelective(permissionPo);
		}
		return update;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int deletePermission(String id) throws BizException {
		// TODO Auto-generated method stub
		permissionDao.deleteRolePermission(id);
		int deletePermission = permissionDao.deletePermission(id);
		permissionDao.deletePermissionByParentId(id);
		log.debug("删除菜单id:{}", id);
		return deletePermission;
	}

	@Override
	public Set<String> checkOwnsPermission(String userId) throws BizException {
		List<Permission> permissions = permissionDao.listByUserId(userId);
		if (CollectionUtils.isEmpty(permissions)) {
			return Collections.emptySet();
		}
		Set<String> set = permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(Permission::getPermission).collect(Collectors.toSet());
		return	set;
	}

	@Override
	public List<Permission> getCurrentPermissions(String userId) throws BizException {
		List<Permission> list = permissionDao.listByUserId(userId);
		final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());
		setChild(permissions);
		List<Permission>  list2  = permissions.stream().filter(p -> p.getParentId().equals("0")).collect(Collectors.toList());
		return list2;
	}
	
	private void setChild(List<Permission> permissions) {
		permissions.parallelStream().forEach(per -> {
			List<Permission> child = permissions.stream().filter(p -> p.getParentId().equals(per.getId()))
					.collect(Collectors.toList());
			per.setChild(child);
		});
	}
}
