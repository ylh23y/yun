package com.yun.security.server.filter;
/*package com.boot.security.server.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.boot.security.server.dto.LoginUser;
import com.boot.security.server.service.TokenService;

*//**
 * Token过滤器
 * 
 * 
 *
 *         2017年10月14日
 *//*
@Component
public class TokenFilter extends OncePerRequestFilter {

	private static final String TOKEN_KEY = "token";

	@Resource
	private TokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		if (StringUtils.isNotBlank(token)) {
			LoginUser loginUser = tokenService.getLoginUser(token);
			if (loginUser != null) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser,
						null, loginUser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}

	*//**
	 * 根据参数或者header获取token
	 * 
	 * @param request
	 * @return
	 *//*
	public static String getToken(HttpServletRequest request) {
		String token = request.getParameter(TOKEN_KEY);
		if (StringUtils.isBlank(token)) {
			token = request.getHeader(TOKEN_KEY);
		}

		return token;
	}

}
*/