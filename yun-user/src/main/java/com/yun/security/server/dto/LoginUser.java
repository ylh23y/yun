package com.yun.security.server.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yun.security.server.model.Permission;
import com.yun.security.server.model.SysUser;
import com.yun.security.server.utils.DateUtils;

public class LoginUser extends SysUser implements UserDetails {

	private static final long serialVersionUID = -1379274258881257107L;

	private List<Permission> permissions;
	private String token;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
	}

	// 账户是否未过期
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 账户是否未锁定
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return getStatus() != Status.DELETE;
	}

	// 密码是否未过期
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 账户是否激活
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


	public Date getLastPasswordResetDate() {
		// TODO Auto-generated method stub
		Date previousNDate = DateUtils.previousNDate(new Date(),10);
		return previousNDate;
	}

}
