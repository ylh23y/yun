package com.yun.common.enums;
 

public enum BizCodeEnum {
	/** 系統异常码  0~999 */   
	SYSTEM_ERROR(0,"系统异常"),
	SYSTEM_NO_RIGHTS(1,"没有操作权限"),
	SYSTEM_JSON_PASER_ERROR(2,"JSON串转map失败"),
	SYSTEM_EASEMOB_ERROR(3,"调用环信接口失败"),
	SYSTEM_ID_ISNULL(4,"主键ID不能为空"),
	SYSTEM_CALLING_INTERFACE_EXCEPTION(5,"调用接口异常"),
	SYSTEM_CALLING_INTERFACE_FAIL(6,"调用接口失败"),
	SYSTEM_EXCEPITON(99,"应用异常"),
	SYSTEM_EXCEPTION_PLEASE_LATER(500,"系统异常，请稍后!"),
	/** 用户账号模块 1000~1999 */
	ACCOUNT_001(1000+1,"该手机号已经注册过了 "),
	ACCOUNT_002(1000+2,"不是正确的手机号  "),
	ACCOUNT_003(1000+3,"手机唯一识别码，手机号，密码，短信验证码 不能为空"),
	ACCOUNT_004(1000+4,"昵称，性别，生日 不能为空"), 
	ACCOUNT_005(1000+5,"生日不是有效日期"), 
	ACCOUNT_006(1000+6,"用户ID不能为空"), 
	ACCOUNT_007(1000+7,"验证码不正确"), 
	ACCOUNT_008(1000+8,"手机号，密码 不能为空"), 
	ACCOUNT_009(1000+9,"密码不对"),
	ACCOUNT_0010(1000+10,"旧密码不对"),
	ACCOUNT_0011(1000+11,"友盟手机标识（设备别名）不能为空"),
	ACCOUNT_0012(1000+12,"xxx"),
	ACCOUNT_0013(1000+13,"xxx"),
	ACCOUNT_0014(1000+14,"该微信号已经添加过"),
 
	;
	
	private int code;
	private String msg;
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	BizCodeEnum(int code ,String msg){
		this.code = code;
		this.msg = msg;
	}
}
