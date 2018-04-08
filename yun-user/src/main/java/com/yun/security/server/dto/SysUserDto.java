package com.yun.security.server.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SysUserDto implements Serializable {

	private static final long serialVersionUID = -6525908145032868837L;

	@ApiModelProperty(value = "用户id" )
	private String userId;
	
	@ApiModelProperty(value = "页码" )
	private Integer pageNo;
	
	@ApiModelProperty(value = "每页显示数量" )
	private Integer pageSize;
	
	@ApiModelProperty(value = "头像图片" )
	private String headImgUrl;
	
	@ApiModelProperty(value = "用户名" )
	private String username;
	
	@ApiModelProperty(value = "昵称" )
	private String nickname;
	
	@ApiModelProperty(value = "状态(0：ENABLED:启用;  1:DISABLED:禁用; 2:DELETE:逻辑删除)" )
	private Integer status;
	
	@ApiModelProperty(value = "旧密码" )
	private String oldPassword;
	
	@ApiModelProperty(value = "新密码" )
	private String newPassword;

		
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	
}
