package com.yun.security.server.dto;

import java.io.Serializable;

public class PermissionDto implements Serializable{

	private static final long serialVersionUID = -184009306207076712L;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



}
