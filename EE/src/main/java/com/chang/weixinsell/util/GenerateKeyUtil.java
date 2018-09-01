package com.chang.weixinsell.util;

import java.util.Random;

/**
 * 生成随机数工具类
 */
public class GenerateKeyUtil {
    /**
     * 生成6位随机字符串作为主键
     *
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
