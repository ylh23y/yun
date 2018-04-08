package com.yun.common.api.bean;

import java.io.Serializable;

import com.yun.common.enums.BizCodeEnum;
import com.yun.common.enums.ResponseCodeEnum;



/**
 * 出参数据
 * @author Administrator
 *
 * @param <T>
 */
public class Result<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2483595531745608858L;

	/**
	 * 错误码
	 */
	private int code;

	/**
	 * 数据
	 */
	private T data;

	/**
	 * 提示信息
	 */
	private String msg = "";

	/**
	 * 业务码
	 */
	private int bizCode;

	public Result() {
		super();
	}

	private Result(int code) {
		super();
		this.code = code;
	}

	private Result(int code, int bizCode, String msg) {
		super();
		this.code = code;
		this.bizCode = bizCode;
		this.msg = msg;
	}

	private Result(int code, T data) {
		super();
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 执行成功无数据返回
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T returnResult() {
		return (T) new Result<Object>(ResponseCodeEnum.OK.getCode());
	}

	/**
	 * 执行成功并返回数据
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T returnResult(Object data) {
		return (T) new Result<Object>(ResponseCodeEnum.OK.getCode(), data);
	}

	/**
	 * 执行成功但有业务异常返回
	 * 
	 * @param bizCode
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T returnExcetionResult(int bizCode, String msg) {
		return (T) new Result<Object>(ResponseCodeEnum.ERROR.getCode(), bizCode, msg);
	}


	/**
	 * 执行失败，
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T returnErrorResult(String msg) {
		return (T) new Result<Object>(ResponseCodeEnum.ERROR.getCode(), BizCodeEnum.SYSTEM_ERROR.getCode(),
				 msg);
	}
}