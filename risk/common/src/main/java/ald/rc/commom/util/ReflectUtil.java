package ald.rc.commom.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectUtil {

    public static Object invokeGetMethod(Class<?> claszz, Object o, String name) {
        Object ret = null;
        try {
            Method method = claszz.getMethod("get" + StringUtils.capitalize(name));
            ret = method.invoke(o);
        } catch (Exception e) {
        }
        return ret;
    }

    public static Object invokeSetMethod(Class<?> claszz, Object o, String name, Class<?>[] argTypes, Object[] args) {
        Object ret = null;
        try {
            Method method = claszz.getMethod("set" + StringUtils.capitalize(name), argTypes);
            ret = method.invoke(o, args);
        } catch (Exception e) {
        }
        return ret;
    }

    /**
     * 是否为常量
     *
     * @param modifiers
     * @return 常量返回true，非常量返回false
     */
    private static boolean isConstant(int modifiers) {
        if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
            return true;
        }
        return false;
    }

}
