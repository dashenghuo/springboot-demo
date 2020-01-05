package com.dalishen.demo.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 枚举帮助类，提供防止code重复，根据code获取枚举
 */
public class EnumUtil {

    private EnumUtil() {
    }

    private static Map<String, Object> cache = new ConcurrentHashMap<>();

    public static <T extends Enum> void putEnum(int code, T t) {
        String key = t.getClass().toString().concat("_").concat(Integer.toString(code));
        if (cache.containsKey(key)) {
            throw new IllegalStateException("code 已存在！" + t);
        }
        cache.put(key, t);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum> T getEnum(Class<T> clazz, String code) {
        return (T) cache.get(clazz.toString().concat("_").concat(code));
    }
}
