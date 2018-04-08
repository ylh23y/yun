package com.yun.security.server.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.yun.security.server.dto.LoginUser;

public class UserUtil {

	public static LoginUser getLoginUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (LoginUser) authentication.getPrincipal();
		}

		return null;
	}

}
