package com.chang.weixinsell.util;

import com.chang.weixinsell.enums.IEnumCode;

public class EnumUtil {
    public static <T extends IEnumCode> T getEnumDataByCode(Integer code, Class<T> cls) {
        for (T each : cls.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
