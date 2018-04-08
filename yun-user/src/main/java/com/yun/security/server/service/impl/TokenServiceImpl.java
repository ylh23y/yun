/*package com.yun.security.server.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yun.security.server.dto.LoginUser;
import com.yun.security.server.dto.Token;
import com.yun.security.server.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	//token过期秒数
	@Value("${token.expire.seconds}")
	private Integer expireSeconds;
	@Resource
	private RedisTemplate<String, LoginUser> redisTemplate;
	@Resource
	private RedisTemplate<String, String> idTokenRedisTemplate;
	//@Resource
	//private SysLogService logService;

	@Override
	public Token saveToken(LoginUser loginUser) {
		String token = getTokenByUserId(loginUser.getId());
		if (StringUtils.isEmpty(token)) {
			token = UUID.randomUUID().toString();
		}

		loginUser.setToken(token);
		updateLoginUser(loginUser);
		//logService.save(loginUser.getId(), "登陆", true, null);

		return new Token(token);
	}

	*//**
	 * 更新缓存的用户信息
	 *//*
	@Override
	public void updateLoginUser(LoginUser loginUser) {
		redisTemplate.boundValueOps(getTokenKey(loginUser.getToken())).set(loginUser, expireSeconds, TimeUnit.SECONDS);
		idTokenRedisTemplate.boundValueOps(getUserIdKey(loginUser.getId())).set(loginUser.getToken(), expireSeconds,
				TimeUnit.SECONDS);
	}

	@Override
	public LoginUser getLoginUser(String token) {
		return redisTemplate.boundValueOps(getTokenKey(token)).get();
	}

	@Override
	public boolean deleteToken(String token) {
		String key = getTokenKey(token);
		LoginUser loginUser = redisTemplate.opsForValue().get(key);
		if (loginUser != null) {
			redisTemplate.delete(key);
			redisTemplate.delete(getUserIdKey(loginUser.getId()));
			//logService.save(loginUser.getId(), "退出", true, null);

			return true;
		}

		return false;
	}

	private String getTokenKey(String token) {
		return "tokens:" + token;
	}

	private String getUserIdKey(String userId) {
		return "users:id:" + userId;
	}

	*//**
	 * 根据userId获取token
	 *//*
	@Override
	public String getTokenByUserId(String userId) {
		return idTokenRedisTemplate.opsForValue().get(getUserIdKey(userId));
	}

}
*/