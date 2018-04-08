package com.yun.common.enums;

public enum ResponseCodeEnum {
	OK(200),
	ERROR(500);

	private int code;
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	ResponseCodeEnum(int code)
	{
		this.code = code;
	}
}
