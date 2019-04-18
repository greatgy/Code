package com.genius.core.base.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author architect.bian
 *
 */
public class EnumUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(EnumUtil.class);
	
	/**
	 * 仅对含有get方法的枚举有效
	 * @param type
	 * @param v
	 * @return
	 */
	public static <T> Object newInstance(Class<T> type, Object v) {
		if (v == null || !StrUtil.isNumeric(v.toString())) {
			return null;
		}
		return newInstance(type, Integer.valueOf(v.toString()));
	}
	
	/**
	 * 仅对含有get方法的枚举有效
	 * @param type
	 * @param v
	 * @return
	 */
	public static <T> Object newInstance(Class<T> type, Integer v) {
		Method method = null;
		try {
			method = type.getMethod("get", int.class);
			return method.invoke(type, v);
		} catch (IllegalAccessException e) {
			logException(log, e);
		} catch (IllegalArgumentException e) {
			logException(log, e);
		} catch (InvocationTargetException e) {
			logException(log, e);
		} catch (NoSuchMethodException e) {
			logException(log, e);
		} catch (SecurityException e) {
			logException(log, e);
		}
		return null;
	}
	
	public static <E extends Enum<E>> E get(Class<E> type, Object v) {
		if (v == null) {
			return null;
		}
		if (StrUtil.isNumeric(v.toString())) {
			return get(type, Integer.parseInt(v.toString()));
		} else {
			return Enum.valueOf(type, v.toString());
		}
	}
	
	public static <E extends Enum<E>> E get(Class<E> type, String v) {
		if (v == null) {
			return null;
		}
		if (StrUtil.isNumeric(v)) {
			return get(type, Integer.parseInt(v));
		} else {
			return Enum.valueOf(type, v);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <E extends Enum<E>> E get(Class<E> type, int v) {
		Method method = null;
		E result = null;
		try {
			method = type.getMethod("get", int.class);
			result = (E) method.invoke(type, v);
		} catch (SecurityException e) {
			logException(log, e);
		} catch (IllegalAccessException e) {
			logException(log, e);
		} catch (IllegalArgumentException e) {
			logException(log, e);
		} catch (InvocationTargetException e) {
			logException(log, e);
		} catch (NoSuchMethodException e) {
			result = Enum.valueOf(type, String.valueOf(v));
			logException(log, e);
		}
		return result;
	}
}
