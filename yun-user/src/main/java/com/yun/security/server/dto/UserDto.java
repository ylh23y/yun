package com.yun.security.server.dto;

import java.util.List;

import com.yun.security.server.model.SysUser;

import io.swagger.annotations.ApiModelProperty;

public class UserDto extends SysUser {

	private static final long serialVersionUID = -184009306207076712L;

	private List<String> roleIds;
	@ApiModelProperty(value = "所属门店id" )
	private String shopId;
	@ApiModelProperty(value = "制作商id" )
	private String manufacturerId;

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	
}
