package com.yun.security.server.dto;

import java.util.List;

import com.yun.security.server.model.Role;

import io.swagger.annotations.ApiModelProperty;

public class RoleDto extends Role {

	private static final long serialVersionUID = 4218495592167610193L;
	
	@ApiModelProperty(value = "页码" )
	private Integer pageNo;
	
	@ApiModelProperty(value = "每页显示数量" )
	private Integer pageSize;

	private List<String> permissionIds;

	public List<String> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<String> permissionIds) {
		this.permissionIds = permissionIds;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
