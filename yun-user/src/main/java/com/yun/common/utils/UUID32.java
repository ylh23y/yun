package com.yun.common.utils;

import java.util.UUID;

/**
 * @author zhangjunhui
 */
public class UUID32 {
    public static String HMID() {
        return UUID.randomUUID().toString();
    }
}
