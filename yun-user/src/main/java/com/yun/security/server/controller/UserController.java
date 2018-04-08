package com.yun.security.server.controller;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yun.common.api.bean.Result;
import com.yun.security.server.Helper.UserHelper;
import com.yun.security.server.dto.SysUserDto;
import com.yun.security.server.dto.UserDto;
import com.yun.security.server.model.SysUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户相关接口
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	private  Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private UserHelper helper;
	

	@ApiOperation(value="分页获取所有用户列表", 
    		 notes="输入参数：SysUserDto对象的pageNo（1开始）、pageSize或者username或者nickname或者status需要传<br>",
 			 httpMethod = "POST")
    @ApiImplicitParams({
   	 @ApiImplicitParam(name = "Authorization", paramType="header",value = "Authorization", required = true, dataType = "String"),
    })
	@RequestMapping("/query/getAllUsers")
	@PostMapping
	//@PreAuthorize("hasAuthority('sys:user:query')")
	public Result<String> getAllUsers(@RequestBody SysUserDto userDto) {
	   log.debug("getAllUsers");
		try {					
			return this.helper.getAllUsers(userDto);								
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return Result.returnErrorResult(e.getLocalizedMessage());
		}
	}  
	  



	@ApiOperation(value = "根据用户id获取用户", 
	 notes="",
		 httpMethod = "GET")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", paramType="header",value = "Authorization", required = true, dataType = "String"),
	})
	@GetMapping("/query/getUserById/{id}")
	public Result<String> getUserById(@PathVariable String id) {
		log.debug("getUserById");
		try {					
			return this.helper.getUserById(id);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return Result.returnErrorResult(e.getLocalizedMessage());
		}
	}

}
