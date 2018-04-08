package com.yun.common.exception;
/**
 * 业务异常类
 *
 */
public class BizException extends Exception {   

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 错误码
	 */
	private int bizCode;
	
	/**
	 * 错误描述
	 */
	private String msg;
	
	public BizException(int bizCode, String msg){
		this.bizCode = bizCode;
		this.msg = msg;
	}
	
	public BizException(String msg){
		this.bizCode = 500;
		this.msg = msg;
	}
	public int getBizCode() {
		return bizCode;
	}
	public void setBizCode(int bizCode) {
		this.bizCode = bizCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
