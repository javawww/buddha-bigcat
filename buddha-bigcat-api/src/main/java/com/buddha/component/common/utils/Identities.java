package com.buddha.component.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

public class Identities {
    private static SecureRandom random = new SecureRandom();

    private Identities() {
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成,中间有-分割
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成,中间无-分割
     */
    public static String uuid2() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return random.nextLong();
    }
}
