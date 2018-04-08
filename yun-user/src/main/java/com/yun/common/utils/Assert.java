package com.yun.common.utils;

import org.apache.commons.lang.StringUtils;

import com.yun.common.exception.BizException;

/**
 * 数据校验
 */
public abstract class Assert {

    public static void isBlank(String str, String message) throws BizException {
        if (StringUtils.isBlank(str)) {
            throw new BizException(message);
        }
    }

    public static void isNull(Object object, String message) throws BizException {
        if (object == null) {
            throw new BizException(message);
        }
    }
}
